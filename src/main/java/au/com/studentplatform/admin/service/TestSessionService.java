package au.com.studentplatform.admin.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.studentplatform.admin.model.TestStatus;
import au.com.studentplatform.admin.model.dto.RecentActivityDTO;
import au.com.studentplatform.admin.repository.TestSessionRepository;

@Service
@Transactional
public class TestSessionService {
	private final TestSessionRepository repo;

	public TestSessionService(TestSessionRepository repo) {
		this.repo = repo;
	}
	public List<Integer> getSubjectsByClassId(String userId, TestStatus status) {
		return repo.findSessionIdsByUserIdAndStatus(userId, status);
	}
	
    public List<RecentActivityDTO> getRecentActivities(String email) {
        return repo.recentActivities(
            email,
            PageRequest.of(0, 100)
        );
    }


}
