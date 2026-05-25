package jp.kronos.ems.service;

import jp.kronos.ems.domain.User;

/**
 * 共通ユーティリティ処理を定義するインターフェースです。
 */
public interface UtilService {
	/**
	 * 管理者権限のチェックを行います。
	 * @param user チェック対象のユーザー情報
	 */
	public void adminCheck(User user);

}