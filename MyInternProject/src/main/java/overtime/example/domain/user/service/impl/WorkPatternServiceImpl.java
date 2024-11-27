package overtime.example.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import overtime.example.domain.user.model.WorkPatterns;
import overtime.example.domain.user.service.WorkPatternService;
import overtime.example.repository.WorkPatternMapper;

@Service
public class WorkPatternServiceImpl implements WorkPatternService {
	@Autowired
	private WorkPatternMapper mapper;
	
	/** 勤務パターンマスター取得 */
	public List<WorkPatterns> getWorkPatternMaster() {
		return mapper.findMany();
	};
}
