package overtime.example.repository;

import org.apache.ibatis.annotations.Mapper;

import overtime.example.domain.user.model.Users;

@Mapper
public interface UserMapper {

	/** ログインユーザー情報取得 */
	public Users findLoginUser(String account);
}
