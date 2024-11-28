package overtime.example.repository;

import org.apache.ibatis.annotations.Mapper;

import overtime.example.domain.user.model.Reports;

@Mapper
public interface ReportMapper {

	/** 報告データ1件作成 */
	public int insertOne(Reports report);
	
}
