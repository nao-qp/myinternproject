package overtime.example.domain.user.service.impl;

import java.util.List;

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
	
	/** 社員/報告データ一一覧取得 */
	@Override
	public List<Reports> getReportList(Integer id) {
		return mapper.findMany(id);
	}
	
	/** 社員/報告データ1件取得 */
	@Override
	public Reports getReport(Integer id) {
		return mapper.findOne(id);
	}
	
	/** 社員/報告データ更新 */
	@Override
	public int editReport(Reports report) {
		return mapper.updateOne(report);
	}
	
	/** 社員/報告データ新規1件作成（事後報告） */
	@Override
	public int addNewReport(Reports report) {
		return mapper.insertNewOne(report);
	}
	
	/** 次長/報告データ一一覧取得 */
	@Override
	public List<Reports> getCheckDataList() {
		return mapper.findManyCheckData();
	}
	
	/** 次長//残業報告確認更新処理 */
	@Override
	public int updChecked(Integer id) {
		return mapper.updateOneChecked(id);
	}
	
	/** 月次資料CSV出力データ一覧取得（全件） */
	@Override
	public List<Reports> getMonthlyreportListAll() {
		return mapper.findManyMonthlyAll();
	}
}
