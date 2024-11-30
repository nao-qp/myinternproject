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
	
	/** 社員/報告データ更新 */
	public int editReport(Reports report);
	
	/** 社員/報告データ新規1件作成（事後報告） */
	public int addNewReport(Reports report);
	
	/** 次長/報告データ一一覧取得 */
	public List<Reports> getCheckDataList();
	
	/** 次長//残業報告確認更新処理 */
	public int updChecked(Integer id);
	
	/** 月次資料CSV出力データ一覧取得（全件） */
	public List<Reports> getMonthlyreportListAll();
}
