package jp.kronos.ems.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

/**
 * 従業員登録・更新時の入力データを保持するフォームクラスです。
 */
@Data
public class EmpForm {

	/** 社員番号 (必須、30文字以内) */
	@NotBlank(message = "社員番号を入力してください")
	@Size(max = 30, message = "社員番号は30文字以内で入力してください")
	private String enumber;

	/** 氏名 (必須、100文字以内) */
	@NotBlank(message = "氏名を入力してください")
	@Size(max = 100, message = "氏名は100文字以内で入力してください")
	private String ename;

	/** 入社日 (必須) */
	@NotBlank(message = "入社日を入力してください")
	private String hireDate;

	/** 部署ID (必須) */
	@NotNull(message = "部署を選択してください")
	private Integer deptId;

	/** 電話番号 (必須、日本国内の電話番号形式) */
	@NotBlank(message = "電話番号を入力してください")
	@Pattern(regexp = "^(?!0{11})(0\\d{9,10}|0\\d{1,4}-\\d{1,4}-\\d{4})$",
			message = "正しい電話番号を入力してください")
	private String telNumber;

	/** メールアドレス (必須、メールアドレス形式) */
	@Email(message = "正しいメールアドレスを入力してください")
	@NotBlank(message = "メールアドレスを入力してください")
	private String email;

	/** 住所 (必須、500文字以内) */
	@NotBlank(message = "住所を入力してください")
	@Size(max = 500, message = "住所は500文字以内で入力してください")
	private String address;
}