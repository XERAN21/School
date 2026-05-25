package jp.kronos.ems.domain;

import lombok.Data;
/**
 * 部署情報を保持するドメインクラスです。
 */
@Data
public class Dept {
	/** 部署ID */
	private int id;
	/** 部署名 */
	private String dname;
	/** 備考（概要） */
	private String description;

}