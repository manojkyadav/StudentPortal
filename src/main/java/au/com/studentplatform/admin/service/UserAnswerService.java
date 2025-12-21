package au.com.studentplatform.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.studentplatform.admin.model.UserAnswer;
import au.com.studentplatform.admin.repository.UserAnswerRepository;

@Service
@Transactional
public class UserAnswerService {

	@Autowired
	UserAnswerRepository userAnswerRepository;

	public List<UserAnswer> findBySessionId(Integer sessionId)
	{
		List<UserAnswer> userAnswer = userAnswerRepository.findBySessionId(sessionId);

		return userAnswer;
	}

}
