package jp.kronos.ems.service;

import jp.kronos.ems.domain.User;

/**
 * ログイン認証に関する業務ロジックを定義するインターフェースです。
 */
public interface LoginService {

	/**
	 * ユーザーの認証を行います。
	 * @param userId ユーザーID
	 * @param password パスワード
	 * @return 認証されたユーザー情報、失敗時はnull
	 */
	User authenticate(String userId, String password);

}