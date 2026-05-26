package jp.kronos.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.kronos.ems.domain.Emp;
import jp.kronos.ems.domain.EmpDetail;
import jp.kronos.ems.form.EmpForm;
import jp.kronos.ems.repository.EmpDetailRepository;
import jp.kronos.ems.repository.EmpRepository;

@Service
public class EmpServiceImpl implements EmpService {
	
	private final EmpRepository empRepository;
	private final EmpDetailRepository empDetailRepository;
	
	public EmpServiceImpl(EmpRepository repository, EmpDetailRepository empDetailRepository) {
		this.empRepository = repository;
		this.empDetailRepository = empDetailRepository;
	}

	@Override
	public List<Emp> getEmpsByDeptId(int deptId) {
		return empRepository.findByDeptId(deptId);
	}

	@Override
	public Emp getEmp(int id) {
		return empRepository.findById(id);
	}

	@Override
	@Transactional
	public void saveEmp(EmpForm form) {
	 int count = empRepository.countByEnumber(form.getEnumber());
		
	 if (count >= 1) {
		throw new RuntimeException("この社員番号は既に登録されてます。");
	}else {
		Emp emp = new Emp();
		emp.setEnumber(form.getEnumber());
		emp.setEname(form.getEname());
		emp.setHireDate(form.getHireDate());
		emp.setDeptId(Integer.parseInt(form.getDeptId()));
		emp.setTelNumber(form.getTelNumber());
		emp.setEmail(form.getEmail());
		emp.setAddress(form.getAddress());
		
		empRepository.save(emp);
		
		EmpDetail detail = new EmpDetail();
		detail.setEmpId(emp.getId());
		detail.setTelNumber(emp.getTelNumber());
		detail.setEmail(emp.getEmail());
		detail.setAddress(emp.getAddress());
		
		empDetailRepository.save(detail);
	}
		
	}

}
