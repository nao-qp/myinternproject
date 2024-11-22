package overtime.example.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import overtime.example.domain.user.model.Requests;
import overtime.example.domain.user.service.RequestService;
import overtime.example.repository.RequestMapper;


@Service
public class RequestServiceImpl implements RequestService {
	@Autowired
	private RequestMapper mapper;
	
	/** 残業申請データ取得 */
	@Override
	public List<Requests> getRequestList(Integer id) {
		return mapper.findMany(id);
	}
	
	/** 残業申請データ1件取得 */
	@Override
	public Requests getRequest(Integer id) {
		return mapper.findOne(id);
	}
}
