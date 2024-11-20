package overtime.example.domain.user.service;

import overtime.example.domain.user.model.Users;

public interface UserService {

	/** ログインユーザー情報取得 */
	public Users getLoginUser(String account);
}
