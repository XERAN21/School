package jp.kronos.ems.controller;

import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.kronos.ems.domain.Emp;
import jp.kronos.ems.domain.User;
import jp.kronos.ems.service.EmpService;

@Controller
@RequestMapping("/employees")
public class EmpController {
	
	private final EmpService empService;

	public EmpController(EmpService service) {
		this.empService = service;
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
		
		model.addAttribute("emp",emp);
		
		return "emp/detail";
	}
}
