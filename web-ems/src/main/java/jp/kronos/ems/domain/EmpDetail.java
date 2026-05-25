package jp.kronos.ems.domain;

import lombok.Data;

/**
 * 従業員の詳細情報（連絡先等）を保持するドメインクラスです。
 */
@Data
public class EmpDetail {
	/** 詳細内部ID */
	private int id; 
	/** 従業員ID（外部キー） */
	private int empId; 
	/** 電話番号 */
	private String telNumber; 
	/** メールアドレス */
	private String email; 
	/** 住所 */
	private String address; 
}