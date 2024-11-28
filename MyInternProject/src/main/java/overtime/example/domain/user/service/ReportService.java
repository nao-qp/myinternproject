package overtime.example.domain.user.service;

import overtime.example.domain.user.model.Reports;

public interface ReportService {

	/** 報告データ1件作成 */
	public int addReport(Reports report);
}
