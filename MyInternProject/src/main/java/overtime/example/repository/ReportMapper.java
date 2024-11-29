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
}
