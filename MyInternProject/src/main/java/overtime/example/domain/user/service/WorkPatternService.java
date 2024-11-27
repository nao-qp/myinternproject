package overtime.example.domain.user.service;

import java.util.List;

import overtime.example.domain.user.model.WorkPatterns;

public interface WorkPatternService {

	/** 勤務パターンマスター取得 */
	public List<WorkPatterns> getWorkPatternMaster();
}
