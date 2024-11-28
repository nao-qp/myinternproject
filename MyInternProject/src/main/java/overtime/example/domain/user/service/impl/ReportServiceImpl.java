package overtime.example.domain.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import overtime.example.domain.user.model.Reports;
import overtime.example.domain.user.service.ReportService;
import overtime.example.repository.ReportMapper;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private ReportMapper mapper;
	
	/** 報告データ1件作成 */
	@Override
	public int addReport(Reports report) {
		return mapper.insertOne(report);
	}
	
}
