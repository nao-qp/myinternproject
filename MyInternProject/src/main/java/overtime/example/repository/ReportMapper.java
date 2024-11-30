package overtime.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import overtime.example.domain.user.model.Reports;

@Mapper
public interface ReportMapper {

	/** 報告データ1件作成 */
	public int insertOne(Reports report);
	
	/** 社員/報告データ一一覧取得 */
	public List<Reports> findMany(Integer id);
	
	/** 社員/報告データ1件取得 */
	public Reports findOne(Integer id);
	
	/** 社員/報告データ更新 */
	public int updateOne(Reports report);
	
	/** 社員/報告データ新規1件作成（事後報告） */
	public int insertNewOne(Reports report);
	
	/** 次長/報告データ一一覧取得 */
	public List<Reports> findManyCheckData();
	
	/** 次長//残業報告確認更新処理 */
	public int updateOneChecked(Integer id);
	
	/** 月次資料CSV出力データ一覧取得（全件） */
	public List<Reports> findManyMonthlyAll();
}
