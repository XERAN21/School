package jp.kronos.ems.repository;

import org.springframework.stereotype.Repository;

import jp.kronos.ems.domain.User;
import jp.kronos.ems.mapper.UserMapper;

/**
 * ユーザー情報のリポジトリ実装クラスです。
 */
@Repository
public class DBUserRepository implements UserRepository {
	
	private final UserMapper userMapper;

    /**
     * コンストラクタ。
     * @param userMapper ユーザーマッパー
     */
    public DBUserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

	@Override
	public User findByUserIdAndPassword(String userId, String password) {
		return userMapper.findByUserIdAndPassword(userId, password);
	}
}