package jp.kronos.ems.service;

import org.springframework.stereotype.Service;

import jp.kronos.ems.domain.User;

/**
 * 共通ユーティリティ処理の実装クラスです。
 */
@Service
public class UtilServiceImpl implements UtilService {

	/**
	 * ユーザーが管理者権限を持っているかチェックします。
	 * 管理者でない場合は例外をスローします。
	 * @param user ユーザー情報
	 * @throws RuntimeException 管理者権限がない場合
	 */
	@Override
	public void adminCheck(User user) {
		if (user.getAdminFlg() != 1) {
			throw new RuntimeException("403 権限がありません。");
		}
	}
}