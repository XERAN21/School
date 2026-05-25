package jp.kronos.ems.domain;

import lombok.Data;

/**
 * 従業員の基本情報を保持するドメインクラスです。
 */
@Data
public class Emp {
	/** 従業員内部ID */
	private int id;
	/** 社員番号 */
	private String enumber;
	/** 氏名 */
	private String ename;
	/** 入社日 */
	private String hireDate;
	/** 部署ID */
	private int deptId;
	/** 部署名 */
	private String dname;
	/** 電話番号（詳細情報からの結合用） */
	private String telNumber;
	/** メールアドレス（詳細情報からの結合用） */
	private String email;
	/** 住所（詳細情報からの結合用） */
	private String address;
	
}