package jp.kronos.ems.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jp.kronos.ems.domain.Emp;
import jp.kronos.ems.mapper.EmpMapper;

/**
 * 従業員情報のリポジトリ実装クラスです。
 */
@Repository
public class DBEmpRepository implements EmpRepository {

	private final EmpMapper empMapper;

	/**
	 * コンストラクタ。
	 * @param empMapper 従業員マッパー
	 */
	public DBEmpRepository(EmpMapper empMapper) {
		this.empMapper = empMapper;
	}

	@Override
	public void deleteByDeptId(int deptId) {
		empMapper.deleteByDeptId(deptId);
	}

	@Override
	public List<Emp> findByDeptId(int deptId) {
		return empMapper.findByDeptId(deptId);
	}

	@Override
	public Emp findById(int id) {
		return empMapper.findById(id);
	}

	@Override
	public void save(Emp emp) {
		empMapper.save(emp);
	}

	@Override
	public int countByEnumber(String Enumber) {
		return empMapper.countByEnumber(Enumber);
	}
	
	

}