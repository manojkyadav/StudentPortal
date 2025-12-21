package au.com.studentplatform.admin.service;

import au.com.studentplatform.admin.model.TestSession;

public interface TestService {

    TestSession createSession(Integer topicId, String mode, String email);

    void saveAnswer(Integer sessionId, Integer questionId, Integer optionId);

    void evaluateTest(Integer sessionId);
}
