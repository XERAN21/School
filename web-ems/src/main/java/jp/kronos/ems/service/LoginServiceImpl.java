package jp.kronos.ems.service;

import org.springframework.stereotype.Service;

import jp.kronos.ems.domain.User;
import jp.kronos.ems.repository.UserRepository;

/**
 * ログイン認証サービスの実装クラスです。
 */
@Service
public class LoginServiceImpl implements LoginService {

	private final UserRepository userRepository;

	/**
	 * コンストラクタ。
	 * @param userRepository ユーザーリポジトリ
	 */
	public LoginServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User authenticate(String userId, String password) {
		return userRepository.findByUserIdAndPassword(userId, password);
	}
}