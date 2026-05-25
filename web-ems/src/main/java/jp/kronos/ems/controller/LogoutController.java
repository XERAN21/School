package jp.kronos.ems.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ログアウト処理を制御するコントローラークラスです。
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {

    /**
     * ログアウト処理を実行し、セッションを破棄します。
     * @param session セッションオブジェクト
     * @return ログイン画面へのリダイレクト
     */
    @PostMapping
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}