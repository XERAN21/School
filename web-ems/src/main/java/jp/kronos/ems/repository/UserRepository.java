package jp.kronos.ems.repository;

import jp.kronos.ems.domain.User;

/**
 * ユーザー情報（認証用）に関するデータアクセスを定義するインターフェースです。
 */
public interface UserRepository {
	/**
	 * ユーザーIDとパスワードからユーザー情報を検索します。
	 * @param userId ユーザーID
	 * @param password パスワード
	 * @return ユーザードメイン
	 */
	User findByUserIdAndPassword(String userId, String password);
}