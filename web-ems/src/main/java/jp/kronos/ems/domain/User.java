package jp.kronos.ems.domain;

import lombok.Data;

/**
 * システムを利用するユーザー（管理者・一般）の認証情報を保持するドメインクラスです。
 */
@Data
public class User {
    /** ユーザー内部ID */
    private Integer id;
    /** ユーザーID（ログイン用） */
    private String userId;
    /** パスワード */
    private String password;
    /** ユーザー氏名 */
    private String name;
    /** 管理者フラグ（1:管理者、0:一般ユーザー） */
    private Integer adminFlg;
}