package overtime.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import overtime.example.domain.user.model.Requests;

@Mapper
public interface RequestMapper {

	/** 残業申請データ一覧取得 */
	public List<Requests> findMany(Integer id);
	
	/** 残業申請データ1件取得 */
	public Requests findOne(Integer id);
}
