package au.com.studentplatform.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.studentplatform.admin.model.dto.DashboardStatsDTO;
import au.com.studentplatform.admin.model.dto.RecentActivityDTO;
import au.com.studentplatform.admin.model.dto.TopicPerformanceDTO;
import au.com.studentplatform.admin.repository.TestSessionRepository;

@Service
@Transactional
public class DashboardService {
	private final TestSessionRepository testSessionRepository;

    public DashboardService(TestSessionRepository testSessionRepository) {
        this.testSessionRepository = testSessionRepository;
    }

    public DashboardStatsDTO getStats(String email) {

        DashboardStatsDTO dto = new DashboardStatsDTO();
        dto.setTestsCompleted(
            testSessionRepository.countCompletedTests(email)
        );

        dto.setAverageScore(
            Optional.ofNullable(
                testSessionRepository.findAverageScore(email)
            ).orElse(0.0)
        );

        dto.setTopicsStudied(
            testSessionRepository.countDistinctTopics(email)
        );

        return dto;
    }

    public List<TopicPerformanceDTO> getTopicPerformance(String email) {
        return testSessionRepository.topicPerformance(email);
    }

    public List<RecentActivityDTO> getRecentActivities(String email) {
        return testSessionRepository.recentActivities(
            email,
            PageRequest.of(0, 5)
        );
    }

}
