package jp.kronos.ems.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.kronos.ems.domain.Dept;
import jp.kronos.ems.domain.User;
import jp.kronos.ems.form.DeptForm;
import jp.kronos.ems.service.DeptService;
import jp.kronos.ems.service.UtilService;

/**
 * 部署管理に関するリクエストを制御するコントローラークラスです。
 */
@Controller
@RequestMapping("/depts")
public class DeptController {

	private final DeptService deptService;
	private final UtilService utilService;

	/**
	 * コンストラクタ。
	 * @param deptService 部署サービス
	 * @param utilService 共通ユーティリティサービス
	 */
	public DeptController(DeptService deptService, UtilService utilService) {
		this.deptService = deptService;
		this.utilService = utilService;
	}

	/**
	 * 部署一覧画面を表示します。
	 * @param model 画面へ渡すデータを保持するModelオブジェクト
	 * @param session 現在のユーザーセッション
	 * @return 部署一覧画面のパス、またはログイン画面へのリダイレクト
	 */
	@GetMapping
	public String showDeptList(Model model, HttpSession session) {
		
		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			return "redirect:/login";
		}

		List<Dept> depts = deptService.getDepts();
		model.addAttribute("depts", depts);
		return "dept/list";
	}

	/**
	 * 部署詳細画面を表示します。
	 * @param id 表示対象の部署ID
	 * @param model 画面へ渡すデータを保持するModelオブジェクト
	 * @param session 現在のユーザーセッション
	 * @return 部署詳細画面のパス、またはログイン画面/一覧画面へのリダイレクト
	 */
	@GetMapping("/{id}/detail")
	public String showDeptDetail(@PathVariable Integer id, Model model, HttpSession session) {

		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			return "redirect:/login";
		}

		Dept dept = deptService.getDept(id);
		if (dept == null) {
			return "redirect:/depts";
		}

		model.addAttribute("dept", dept);
		return "dept/detail";
	}

	/**
	 * 部署登録画面を表示します。
	 * @param deptForm 登録用フォームオブジェクト
	 * @param session 現在のユーザーセッション
	 * @return 部署登録画面のパス、またはログイン画面へのリダイレクト
	 */
	@GetMapping("/new")
	public String showNewDept(@ModelAttribute DeptForm deptForm, HttpSession session) {
		
		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			return "redirect:/login";
		}
		utilService.adminCheck(user);

		return "dept/new";
	}

	/**
	 * 部署の登録を実行します。
	 * @param deptForm 入力された部署登録用フォーム
	 * @param result 入力チェック結果
	 * @param session 現在のユーザーセッション
	 * @param ra リダイレクト先へデータを渡すRedirectAttributes
	 * @return 部署一覧へのリダイレクト、または入力エラー時の登録画面のパス
	 */
	@PostMapping("/new")
	public String createDept(@Validated @ModelAttribute DeptForm deptForm, BindingResult result, HttpSession session, RedirectAttributes ra) {

		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			return "redirect:/login";
		}
		utilService.adminCheck(user);

		if (result.hasErrors()) {
			return "dept/new";
		}
		deptService.createDept(deptForm);
		
		ra.addFlashAttribute("notice", "登録が完了しました。");
		return "redirect:/depts";
	}

	/**
	 * 指定されたIDの部署を削除します。
	 * @param id 削除対象の部署ID
	 * @param session 現在のユーザーセッション
	 * @param ra リダイレクト先へデータを渡すRedirectAttributes
	 * @return 部署一覧画面へのリダイレクト
	 */
	@PostMapping("/{id}/delete")
	public String deleteDept(@PathVariable Integer id, HttpSession session, RedirectAttributes ra) {

		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			return "redirect:/login";
		}
		utilService.adminCheck(user);

		deptService.deleteDept(id);
		
		ra.addFlashAttribute("notice", "削除が完了しました。");

		return "redirect:/depts";
	}

	/**
	 * 部署編集画面を表示します。
	 * @param id 更新対象の部署ID
	 * @param model 画面へ渡すデータを保持するModelオブジェクト
	 * @param session 現在のユーザーセッション
	 * @return 部署編集画面のパス、またはログイン画面へのリダイレクト
	 */
	@GetMapping("/{id}/edit")
	public String showEditDept(@PathVariable Integer id, Model model, HttpSession session) {

		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			return "redirect:/login";
		}

		utilService.adminCheck(user);

		Dept dept = deptService.getDept(id);
		
		DeptForm form = new DeptForm();
		form.setDname(dept.getDname());
		form.setDescription(dept.getDescription());
		model.addAttribute("deptForm", form);
		
		model.addAttribute("id", id);
		return "dept/edit";
	}

	/**
	 * 部署の更新を実行します。
	 * @param id 更新対象の部署ID
	 * @param form 入力された部署編集用フォーム
	 * @param result 入力チェック結果
	 * @param session 現在のユーザーセッション
	 * @param model 画面へ渡すデータを保持するModelオブジェクト
	 * @param ra リダイレクト先へデータを渡すRedirectAttributes
	 * @return 部署一覧へのリダイレクト、または入力エラー時の更新画面のパス
	 */
	@PostMapping("/{id}/edit")
	public String updateDept(@PathVariable Integer id, @Validated @ModelAttribute DeptForm form,
			BindingResult result, HttpSession session, Model model, RedirectAttributes ra) {
		
		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			return "redirect:/login";
		}

		utilService.adminCheck(user);

		if (result.hasErrors()) {
			model.addAttribute("id", id);
			return "dept/edit";
		}

		deptService.editDept(form, id);
		
		ra.addFlashAttribute("notice", "更新が完了しました。");
		return "redirect:/depts";
	}
}