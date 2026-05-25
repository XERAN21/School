package jp.kronos.ems.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.kronos.ems.domain.Dept;
import jp.kronos.ems.domain.Emp;
import jp.kronos.ems.form.DeptForm;
import jp.kronos.ems.repository.DeptRepository;
import jp.kronos.ems.repository.EmpDetailRepository;
import jp.kronos.ems.repository.EmpRepository;

/**
 * 部署管理サービスの実装クラスです。
 */
@Service
public class DeptServiceImpl implements DeptService {

	private final DeptRepository deptRepository;
	private final EmpRepository empRepository;
	private final EmpDetailRepository empDetailRepository;

	/**
	 * コンストラクタ。
	 * @param deptRepository 部署リポジトリ
	 * @param empRepository 従業員リポジトリ
	 * @param empRepository 従業員詳細リポジトリ
	 */
	public DeptServiceImpl(DeptRepository deptRepository, EmpRepository empRepository, EmpDetailRepository empDetailRepository) {
		this.deptRepository = deptRepository;
		this.empRepository = empRepository;
		this.empDetailRepository = empDetailRepository;
	}

	@Override
	public List<Dept> getDepts() {
		return deptRepository.findAll();
	}

	@Override
	public Dept getDept(Integer id) {
		return deptRepository.findById(id);
	}

	@Override
	public void createDept(DeptForm form) {
		Dept dept = new Dept();
		dept.setDname(form.getDname());
		dept.setDescription(form.getDescription());
		deptRepository.save(dept);
	}

	@Override
	@Transactional
	public void deleteDept(Integer id) {
		List<Emp> emps = empRepository.findByDeptId(id);
		for (Emp emp : emps) {
			empDetailRepository.deleteByEmpId(emp.getId());
		}
		empRepository.deleteByDeptId(id);
		deptRepository.delete(id);
	}

	@Override
	public void editDept(DeptForm form, int id) {
		Dept dept = new Dept();
		dept.setId(id);
		dept.setDname(form.getDname());
		dept.setDescription(form.getDescription());
		deptRepository.update(dept);
	}
}