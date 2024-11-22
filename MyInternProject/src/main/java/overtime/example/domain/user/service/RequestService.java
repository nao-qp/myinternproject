package overtime.example.domain.user.service;

import java.util.List;

import overtime.example.domain.user.model.Requests;

public interface RequestService {

	/** 残業申請データ一覧取得 */
	public List<Requests> getRequestList(Integer id);
	
	/** 残業申請データ1件取得 */
	public Requests getRequest(Integer id);
}
