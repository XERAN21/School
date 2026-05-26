package jp.kronos.ems.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class EmpForm {
	@NotBlank(message = "社員番号を入力してください")
	private String enumber;

	@NotBlank(message = "氏名を入力してください")
	private String ename;

	@NotBlank(message = "入社日を入力してください")
	private String hireDate;

	@NotBlank(message = "部署を選択してください")
	private String deptId;

	@NotBlank(message = "電話番号を入力してください")
	@Pattern(regexp = "^(?!0{11})(0\\d{9,10}|0\\d{1,4}-\\d{1,4}-\\d{4})$", 
			message = "正しい電話番号を入力してください")
	private String telNumber;

	@Email(message = "正しいメールアドレスを入力してください")
	@NotBlank(message = "メールアドレスを入力してください")
	private String email;

	@NotBlank(message = "住所を入力してください")
	private String address;
}
