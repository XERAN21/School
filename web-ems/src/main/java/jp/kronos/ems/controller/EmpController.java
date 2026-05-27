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
import jp.kronos.ems.domain.Emp;
import jp.kronos.ems.domain.User;
import jp.kronos.ems.form.EmpForm;
import jp.kronos.ems.service.DeptService;
import jp.kronos.ems.service.EmpService;
import jp.kronos.ems.service.UtilService;

/**
 * 従業員管理に関するリクエストを制御するコントローラークラスです。
 */
@Controller
@RequestMapping("/employees")
public class EmpController {

	private final UtilService utilService;
	private final EmpService empService;
	private final DeptService deptService;

	/**
	 * コンストラクタ。
	 * @param empService 従業員サービス
	 * @param utilService 共通ユーティリティサービス
	 * @param deptService 部署サービス
	 */
	public EmpController(EmpService empService, UtilService utilService, DeptService deptService) {
		this.empService = empService;
		this.utilService = utilService;
		this.deptService = deptService;
	}

	/**
	 * 指定された部署に所属する従業員一覧画面を表示します。
	 * @param deptId 表示対象の部署ID
	 * @param model 画面へ渡すデータを保持するModelオブジェクト
	 * @param session 現在のユーザーセッション
	 * @return 従業員一覧画面のパス、またはログイン画面へのリダイレクト
	 */
	@GetMapping("{deptId}")
	public String showEmpList(
			@PathVariable int deptId,
			Model model,
			HttpSession session) {

		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			return "redirect:/login";
		}

		List<Emp> emps = empService.getEmpsByDeptId(deptId);

		model.addAttribute("emps", emps);
		model.addAttribute("deptId", deptId);

		return "emp/list";
	}

	/**
	 * 従業員詳細画面を表示します。
	 * @param id 表示対象の従業員ID
	 * @param session 現在のユーザーセッション
	 * @param model 画面へ渡すデータを保持するModelオブジェクト
	 * @return 従業員詳細画面のパス、またはログイン画面へのリダイレクト
	 */
	@GetMapping("/{id}/detail")
	public String showDetails(
			@PathVariable int id,
			HttpSession session,
			Model model) {

		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			return "redirect:/login";
		}

		Emp emp = empService.getEmp(id);

		model.addAttribute("emp", emp);

		return "emp/detail";
	}

	/**
	 * 従業員登録画面を表示します。
	 * @param empForm 登録用フォームオブジェクト
	 * @param session 現在のユーザーセッション
	 * @param deptId 所属部署ID
	 * @param model 画面へ渡すデータを保持するModelオブジェクト
	 * @return 従業員登録画面のパス、またはログイン画面へのリダイレクト
	 */
	@GetMapping("{deptId}/new")
	public String showNew(
			@ModelAttribute EmpForm empForm,
			HttpSession session,
			@PathVariable int deptId,
			Model model) {

		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			return "redirect:/login";
		}

		utilService.adminCheck(user);

		List<Dept> depts = deptService.getDepts();
		model.addAttribute("depts", depts);
		model.addAttribute("deptId", deptId);
		model.addAttribute("empForm", empForm);

		return "emp/new";
	}

	/**
	 * 従業員の登録を実行します。
	 * @param deptId 所属部署ID
	 * @param empForm 入力された従業員登録用フォーム
	 * @param br 入力チェック結果
	 * @param session 現在のユーザーセッション
	 * @param model 画面へ渡すデータを保持するModelオブジェクト
	 * @param ra リダイレクト先へデータを渡すRedirectAttributes
	 * @return 従業員一覧へのリダイレクト、または入力エラー時の登録画面のパス
	 */
	@PostMapping("{deptId}/new")
	public String create(
			@PathVariable int deptId,
			@Validated @ModelAttribute EmpForm empForm,
			BindingResult br,
			HttpSession session,
			Model model,
			RedirectAttributes ra) {

		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			return "redirect:/login";
		}

		utilService.adminCheck(user);
		List<Dept> depts = deptService.getDepts();

		if (br.hasErrors()) {
			model.addAttribute("depts", depts);
			return "emp/new";
		}

		try {
			empService.saveEmp(empForm);
		} catch (RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("depts", depts);
			return "emp/new";
		}

		ra.addFlashAttribute("notice", "「従業員を登録しました。」");

		return "redirect:/employees/{deptId}";
	}

	/**
	 * 従業員編集画面を表示します。
	 * @param empForm 編集用フォームオブジェクト
	 * @param session 現在のユーザーセッション
	 * @param id 編集対象の従業員ID
	 * @param model 画面へ渡すデータを保持するModelオブジェクト
	 * @return 従業員編集画面のパス、またはログイン画面へのリダイレクト
	 */
	@GetMapping("/{id}/edit")
	public String showEdit(
			@ModelAttribute EmpForm empForm,
			HttpSession session,
			@PathVariable int id,
			Model model) {

		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			return "redirect:/login";
		}

		utilService.adminCheck(user);

		Emp emp = empService.getEmp(id);
		empForm.setEnumber(emp.getEnumber());
		empForm.setEname(emp.getEname());
		empForm.setHireDate(emp.getHireDate());
		empForm.setDeptId(emp.getDeptId());
		empForm.setTelNumber(emp.getTelNumber());
		empForm.setEmail(emp.getEmail());
		empForm.setAddress(emp.getAddress());

		List<Dept> depts = deptService.getDepts();

		model.addAttribute("depts", depts);
		model.addAttribute("empForm", empForm);
		model.addAttribute("deptId", emp.getDeptId());
		model.addAttribute("id", id);

		return "emp/edit";
	}

	/**
	 * 従業員の更新を実行します。
	 * @param form 入力された従業員編集用フォーム
	 * @param br 入力チェック結果
	 * @param id 更新対象の従業員ID
	 * @param session 現在のユーザーセッション
	 * @param model 画面へ渡すデータを保持するModelオブジェクト
	 * @param ra リダイレクト先へデータを渡すRedirectAttributes
	 * @return 従業員一覧へのリダイレクト、または入力エラー時の更新画面のパス
	 */
	@PostMapping("/{id}/edit")
	public String update(
			@Validated @ModelAttribute EmpForm form,
			BindingResult br,
			@PathVariable int id,
			HttpSession session,
			Model model,
			RedirectAttributes ra) {

		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			return "redirect:/login";
		}

		utilService.adminCheck(user);

		List<Dept> depts = deptService.getDepts();

		if (br.hasErrors()) {
			model.addAttribute("depts", depts);
			return "emp/edit";
		}

		try {
			empService.updateEmp(id, form);
		} catch (RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("depts", depts);
			return "emp/edit";
		}

		ra.addFlashAttribute("notice", "「従業員情報を更新しました。」");

		return "redirect:/employees/" + form.getDeptId();
	}

	/**
	 * 指定されたIDの従業員を削除します。
	 * @param id 削除対象の従業員ID
	 * @param session 現在のユーザーセッション
	 * @param ra リダイレクト先へデータを渡すRedirectAttributes
	 * @return 従業員一覧画面へのリダイレクト
	 */
	@PostMapping("/{id}/delete")
	public String delete(
			@PathVariable int id,
			HttpSession session,
			RedirectAttributes ra) {

		User user = (User) session.getAttribute("loginUser");
		if (user == null) {
			return "redirect:/login";
		}

		utilService.adminCheck(user);

		Emp emp = empService.getEmp(id);
		int deptId = emp.getDeptId();

		empService.deleteEmp(id);

		ra.addFlashAttribute("notice", "「従業員を削除しました。」");
		ra.addAttribute("deptId", deptId);

		return "redirect:/employees/{deptId}";
	}
}