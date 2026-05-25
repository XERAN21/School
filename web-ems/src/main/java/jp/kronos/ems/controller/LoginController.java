package jp.kronos.ems.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.kronos.ems.domain.User;
import jp.kronos.ems.form.LoginForm;
import jp.kronos.ems.service.LoginService;

/**
 * ログイン認証に関するリクエストを制御するコントローラークラスです。
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	private final LoginService loginService;
	
	/**
	 * コンストラクタ。
	 * @param loginService ログインサービス
	 */
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	/**
	 * ログイン画面を表示します。
	 * @param loginForm ログインフォームオブジェクト
	 * @return ログイン画面のパス
	 */
	@GetMapping
	public String showLogin(@ModelAttribute LoginForm loginForm) {
		return "login/login";
	}

	/**
	 * ログイン認証処理を実行します。
	 * @param loginForm ログインフォームオブジェクト
	 * @param result 入力チェック結果
	 * @param session セッションオブジェクト
	 * @param model モデルオブジェクト
	 * @return 認証成功時は部署一覧へのリダイレクト、失敗時はログイン画面のパス
	 */
	@PostMapping
	public String login(@Validated @ModelAttribute LoginForm loginForm,
			BindingResult result,
			HttpSession session,
			Model model) {

		if (result.hasErrors()) {
			return "login/login";
		}

		User user = loginService.authenticate(loginForm.getUserId(), loginForm.getPassword());
		if (user == null) {
			model.addAttribute("error", "ユーザーIDまたはパスワードが正しくありません");
			return "login/login";
		}

		session.setAttribute("loginUser", user);
		return "redirect:/depts";
	}
}