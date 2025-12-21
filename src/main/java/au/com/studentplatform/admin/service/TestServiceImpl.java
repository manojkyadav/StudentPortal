package au.com.studentplatform.admin.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import au.com.studentplatform.admin.model.Option;
import au.com.studentplatform.admin.model.Question;
import au.com.studentplatform.admin.model.TestMode;
import au.com.studentplatform.admin.model.TestSession;
import au.com.studentplatform.admin.model.TestStatus;
import au.com.studentplatform.admin.model.UserAnswer;
import au.com.studentplatform.admin.repository.OptionRepository;
import au.com.studentplatform.admin.repository.TestSessionRepository;
import au.com.studentplatform.admin.repository.UserAnswerRepository;

@Service
//@Transactional
public class TestServiceImpl implements TestService {

    private final TestSessionRepository sessionRepo;
    private final UserAnswerRepository answerRepo;
    private final OptionRepository optionRepository;
    private final QuestionService questionService;

    public TestServiceImpl(TestSessionRepository sessionRepo,
                           UserAnswerRepository answerRepo, OptionRepository optionRepository, QuestionService questionService) {
        this.sessionRepo = sessionRepo;
        this.answerRepo = answerRepo;
        this.optionRepository = optionRepository;
        this.questionService = questionService;
    }

    /* ===============================
       1️⃣ Create Test Session
       =============================== */
    @Override
    public TestSession createSession(Integer topicId,String mode , String email) {

        // TODO: replace with logged-in user
       // String email = "";

        TestSession session = new TestSession();
        session.setEmail(email);
        session.setTopicId(topicId);
        session.setMode(TestMode.valueOf(mode));
        session.setStartTime(LocalDateTime.now());
        session.setStatus(TestStatus.STARTED);

        return sessionRepo.save(session);
    }

    /* ===============================
       2️⃣ Save / Update Answer
       =============================== */
    @Override
    public void saveAnswer(Integer sessionId, Integer questionId, Integer optionId) {

        // TODO: replace with real correctness check
        boolean isCorrect = checkIfCorrect(optionId);

        UserAnswer answer = answerRepo
                .findBySessionIdAndQuestionId(sessionId, questionId)
                .orElse(new UserAnswer());

        Question question = questionService.findById(questionId);
        Option option = optionRepository.getById(optionId);
        
        answer.setSessionId(sessionId);
       // answer.setQuestionId(questionId);
        //answer.setSelectedOptionId(optionId);
        answer.setQuestion(question);
        answer.setOption(option);
        answer.setIsCorrect(isCorrect);

        answerRepo.save(answer);
    }

    /* ===============================
       3️⃣ Evaluate Test
       =============================== */
    @Override
    public void evaluateTest(Integer sessionId) {

        TestSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        List<UserAnswer> answers = answerRepo.findBySessionId(sessionId);

        int obtainedMarks = 0;
        int totalMarks = 0;

        for (UserAnswer answer : answers) {
            totalMarks += 1; // or question marks
            if (Boolean.TRUE.equals(answer.getIsCorrect())) {
                obtainedMarks += 1;
            }
        }

        session.setTotalMarks(totalMarks);
        session.setObtainedMarks(obtainedMarks);
        session.setEndTime(LocalDateTime.now());
        session.setStatus(TestStatus.SUBMITTED);

        sessionRepo.save(session);
    }

    /* ===============================
       Utility – Correct Answer Check
       =============================== */
    private boolean checkIfCorrect(Integer optionId) {
    	Boolean correct = optionRepository.isOptionCorrect(optionId);
        return Boolean.TRUE.equals(correct);
    }
}