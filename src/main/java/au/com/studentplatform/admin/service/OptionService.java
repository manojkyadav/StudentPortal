package au.com.studentplatform.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import au.com.studentplatform.admin.model.Option;
import au.com.studentplatform.admin.repository.OptionRepository;

@Service
public class OptionService {
	private final OptionRepository repo;

	public OptionService(OptionRepository repo) {
		this.repo = repo;
	}

	public List<Option> findAll() {
		return repo.findAll();
	}

	public Option findById(Integer id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Option not found"));
	}

	public Option save(Option option) {
		return repo.save(option);
	}
	public List<Option> saveAll(List<Option> options ) {
		return repo.saveAll(options);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
	List<Option> findByQuestionId(Integer questionId){
		return repo.findByQuestionId(questionId);
	}
}
