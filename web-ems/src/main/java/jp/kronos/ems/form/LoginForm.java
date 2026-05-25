package jp.kronos.ems.form;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

/**
 * ログイン画面の入力内容を保持するフォームクラスです。 
 */
@Data
public class LoginForm {
    
    /** ユーザーID */
    @NotBlank(message = "ユーザーIDを入力してください")
    private String userId;
    
    /** パスワード */
    @NotBlank(message = "パスワードを入力してください")
    private String password;

}