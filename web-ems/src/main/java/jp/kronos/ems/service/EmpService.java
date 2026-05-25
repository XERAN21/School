package jp.kronos.ems.service;

import java.util.List;

import jp.kronos.ems.domain.Emp;

public interface EmpService {
	List<Emp> getEmpsByDeptId(int deptId);
	Emp getEmp(int id);
}
