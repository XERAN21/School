package jp.kronos.ems.service;

import java.util.List;

import jp.kronos.ems.domain.Emp;
import jp.kronos.ems.form.EmpForm;

public interface EmpService {
	List<Emp> getEmpsByDeptId(int deptId);
	Emp getEmp(int id);
	void saveEmp(EmpForm form);
	void updateEmp(int id,EmpForm form);
	void deleteEmp(int id);
}
