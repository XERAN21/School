package jp.kronos.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jp.kronos.ems.domain.Emp;
import jp.kronos.ems.repository.EmpRepository;

@Service
public class EmpServiceImpl implements EmpService {
	
	private final EmpRepository empRepository;
	
	public EmpServiceImpl(EmpRepository repository) {
		this.empRepository = repository;
	}

	@Override
	public List<Emp> getEmpsByDeptId(int deptId) {
		return empRepository.findByDeptId(deptId);
	}

	@Override
	public Emp getEmp(int id) {
		return empRepository.findById(id);
	}

}
