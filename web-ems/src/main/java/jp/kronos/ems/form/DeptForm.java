package jp.kronos.ems.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * 部署情報の入力内容を保持するフォームクラスです。
 */
@Data
public class DeptForm {

    /** 部署名 */
    @NotBlank(message = "部署名を入力してください")
    @Size(max = 100, message = "部署名は100文字以内で入力してください")
    private String dname;

    /** 備考 */
    @NotBlank(message = "備考を入力してください")
    @Size(max = 300, message = "備考は300文字以内で入力してください")
    private String description;
}