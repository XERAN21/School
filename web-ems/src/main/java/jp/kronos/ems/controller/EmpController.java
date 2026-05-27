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

@Controller
@RequestMapping("/employees")
public class EmpController {

	private final UtilService utilService;
	private final EmpService empService;
	private final DeptService deptService;

	public EmpController(EmpService service, UtilService utilServiceImpl, DeptService deptService) {
		this.empService = service;
		this.utilService = utilServiceImpl;
		this.deptService = deptService;
	}

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
		
		Emp  emp = empService.getEmp(id);
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
			model.addAttribute("error", e.getMessage() );
			model.addAttribute("depts",depts);
			return "emp/edit";
		}
		
		ra.addFlashAttribute("notice", "「従業員情報を更新しました。」");
		ra.addAttribute("deptId", form.getDeptId());
		 
		return "redirect:/employees/{deptId}";
	}
	
	@PostMapping("/{id}/delete")
	public String delete(
			@PathVariable int id,
			HttpSession session,
			RedirectAttributes ra
			) {
		
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
