package au.com.studentplatform.admin.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import au.com.studentplatform.admin.model.ClassRoom;
import au.com.studentplatform.admin.model.Option;
import au.com.studentplatform.admin.model.Question;
import au.com.studentplatform.admin.model.Subject;
import au.com.studentplatform.admin.model.Topic;
import au.com.studentplatform.admin.repository.ClassRoomRepository;
import au.com.studentplatform.admin.repository.SubjectRepository;
import au.com.studentplatform.admin.repository.TopicRepository;
import au.com.studentplatform.admin.service.QuestionService;

@Service
@Transactional
public class GenericQuestionGenerator {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ClassRoomRepository classRoomRepo;

    @Autowired
    private SubjectRepository subjectRepo;

    @Autowired
    private TopicRepository topicRepo;

    @Value("${google.gemini.api.key}")
    private String geminiApiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String GEMINI_URL ="https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=";

    public void generateQuestions(
            int classId,
            int subjectId,
            String topicName,
            int totalQuestions,
            int batchSize) throws Exception {

        ClassRoom classRoom = classRoomRepo.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        Subject subject = subjectRepo.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        Topic topic = topicRepo.findByTopicName(topicName)
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        int batches = (int) Math.ceil((double) totalQuestions / batchSize);

        for (int batch = 0; batch < batches; batch++) {

            int currentBatchSize =
                    Math.min(batchSize, totalQuestions - batch * batchSize);

            String prompt = """
            		Generate %d Year 7 Australian curriculum mathematics questions 
            		for the topic "%s".

            		Rules:
            		- Output STRICT JSON array only (no markdown, no text)
            		- Use these question types only:
            		  MULTIPLE_CHOICE
            		- For MULTIPLE_CHOICE:
            		  - Provide EXACTLY 4 options
            		  - Only ONE option must be correct

            		JSON format:
            		[
            		  {
            		    "questionTxt": "...",
            		    "questionType": "MULTIPLE_CHOICE",
            		    "difficultyLevel": "EASY | MEDIUM | HARD",
            		    "answer": "...",
            		    "explanation": "...",
            		    "options": [
            		      { "text": "...", "correct": true|false }
            		    ]
            		  }
            		]
            		""".formatted(batchSize, topicName);


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
            String content =
                root.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

            // Parse Gemini JSON output
            QuestionDTO[] questions =
                    objectMapper.readValue(content, QuestionDTO[].class);

            for (QuestionDTO dto : questions) {

                Question question = new Question();
                question.setQuestionTxt(dto.getQuestionTxt());
                question.setQuestionType(dto.getQuestionType());
                question.setDifficultyLevel(dto.getDifficultyLevel());
                question.setAnswer(dto.getAnswer());
                question.setExplanation(dto.getExplanation());
                question.setClassRoom(classRoom);
                question.setSubject(subject);
                question.setTopic(topic);
                question.setOptions(dto.getOptions());

                // Handle options
				/*
				 * if (dto.getOptions() != null) { for (Option optDto : dto.getOptions()) {
				 * Option option = new Option(); option.setOptionTxt(optDto.getText());
				 * option.setCorrect(optDto.isCorrect()); option.setQuestion(question);
				 * question.getOptions().add(option); } }
				 */

                questionService.save(question);
            }


            System.out.println("Saved batch " + (batch + 1));
        }

        System.out.println("All questions generated for topic: " + topicName);
    }

    // DTO for Gemini JSON
	public static class QuestionDTO {
		private String questionTxt;
		private Question.QuestionType questionType;
		private Question.DifficultyLevel difficultyLevel;
		private String answer;
		private String explanation;
		private List<Option> options;

		public String getQuestionTxt() {
			return questionTxt;
		}

		public void setQuestionTxt(String questionTxt) {
			this.questionTxt = questionTxt;
		}

		public Question.QuestionType getQuestionType() {
			return questionType;
		}

		public void setQuestionType(Question.QuestionType questionType) {
			this.questionType = questionType;
		}

		public Question.DifficultyLevel getDifficultyLevel() {
			return difficultyLevel;
		}

		public void setDifficultyLevel(Question.DifficultyLevel difficultyLevel) {
			this.difficultyLevel = difficultyLevel;
		}

		public String getAnswer() {
			return answer;
		}

		public void setAnswer(String answer) {
			this.answer = answer;
		}

		public String getExplanation() {
			return explanation;
		}

		public void setExplanation(String explanation) {
			this.explanation = explanation;
		}

		public List<Option> getOptions() {
			return options;
		}

		public void setOptions(List<Option> options) {
			this.options = options;
		}

	}
}
