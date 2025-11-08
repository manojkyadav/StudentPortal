package com.example.studentplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.studentplatform.model.QuestionOption;
@Repository
public interface OptionRepository extends JpaRepository<QuestionOption, Integer> {
	 List<QuestionOption> findByQuestionId(Integer questionId);
}
