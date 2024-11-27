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
	
	/** 残業申請データ1件更新 */
	public int insertOne(Requests request);
	
	/** 次長//残業申請確認データ一覧取得 */
	public List<Requests> findManyCheckData();
	
	/** 次長//残業申請確認更新処理 */
	public int updateOne(Integer id);
	
	/** 課長//残業申請承認データ一覧取得（次長確認済みデータ） */
	public List<Requests> findManyApproveData();
	
	/** 課長//残業申請承認更新処理 */
	public int updateOneApprove(Integer id);
	
	/** 課長//残業申請差し戻し更新処理 */
	public int updateOneReturn(Integer id);
}
