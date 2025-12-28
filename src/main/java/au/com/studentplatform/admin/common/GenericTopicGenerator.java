package au.com.studentplatform.admin.common;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import au.com.studentplatform.admin.model.ClassRoom;
import au.com.studentplatform.admin.model.Subject;
import au.com.studentplatform.admin.model.Topic;
import au.com.studentplatform.admin.repository.ClassRoomRepository;
import au.com.studentplatform.admin.repository.SubjectRepository;
import au.com.studentplatform.admin.repository.TopicRepository;

@Service
@Transactional
public class GenericTopicGenerator {

    @Autowired
    private ClassRoomRepository classRoomRepo;

    @Autowired
    private SubjectRepository subjectRepo;

    @Autowired
    private TopicRepository topicRepo;

    @Value("${google.gemini.api.key}")
    private String geminiApiKey;

    @Value("${google.gemini.api.url}")
    private String GEMINI_URL;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void generateTopics(
            int classId,
            int subjectId//, int totalTopics
            ) throws Exception {

        ClassRoom classRoom = classRoomRepo.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        Subject subject = subjectRepo.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        String prompt = """
            Generate all topics for "%s" Australian curriculum subject "%s".

            CRITICAL RULES:
            - Return ONLY valid JSON array
            - Do NOT use markdown
            - Do NOT wrap in ```json
            - Output must start with '[' and end with ']'

            JSON format:
            [
              {
                "topicName": "...",
                "description": "..."
              }
            ]
            """
            .formatted(
                //totalTopics,
                classRoom.getClassName(),
                subject.getSubjectName()
            );

        String requestBody = """
            {
              "contents": [{
                "parts": [{
                  "text": "%s"
                }]
              }]
            }
            """.formatted(prompt.replace("\"", "\\\""));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                GEMINI_URL + geminiApiKey,
                HttpMethod.POST,
                entity,
                String.class
        );

        JsonNode root = objectMapper.readTree(response.getBody());
        String content = root.path("candidates")
                .get(0)
                .path("content")
                .path("parts")
                .get(0)
                .path("text")
                .asText();

        // 🔥 CLEAN Gemini formatting issues
        String cleaned = content
                .replaceAll("(?s)```json", "")
                .replaceAll("(?s)```", "")
                .trim();

        if (!cleaned.startsWith("[")) {
            throw new RuntimeException("Invalid Gemini topic JSON:\n" + cleaned);
        }

        TopicDTO[] topics = objectMapper.readValue(cleaned, TopicDTO[].class);

        for (TopicDTO dto : topics) {
            Topic topic = new Topic();
            topic.setTopicName(dto.getTopicName());
            topic.setDescription(dto.getDescription());
            topic.setClassRoom(classRoom);
            topic.setSubject(subject);
            topic.setStatus(Topic.Status.ACTIVE);
            topic.setCreatedAt(LocalDateTime.now());

            topicRepo.save(topic);
        }

        System.out.println("✅ Topics generated successfully for subject: " + subject.getSubjectName());
    }

    // DTO for Gemini
    public static class TopicDTO {
        private String topicName;
        private String description;

        public String getTopicName() {
            return topicName;
        }
        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
    }
}
