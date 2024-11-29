package overtime.example.domain.user.service;

import java.util.List;

import overtime.example.domain.user.model.Reports;

public interface ReportService {

	/** 報告データ1件作成 */
	public int addReport(Reports report);
	
	/** 社員/報告データ一一覧取得 */
	public List<Reports> getReportList(Integer id);
	
	/** 社員/報告データ1件取得 */
	public Reports getReport(Integer id);
}
