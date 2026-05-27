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

	/**
	 * 指定された部署IDに所属する従業員を全て削除します。
	 * @param deptId 部署ID
	 */
	@Override
	public void deleteByDeptId(int deptId) {
		empMapper.deleteByDeptId(deptId);
	}

	/**
	 * 指定された部署IDに所属する従業員一覧を取得します。
	 * @param deptId 部署ID
	 * @return 従業員ドメインオブジェクトのリスト
	 */
	@Override
	public List<Emp> findByDeptId(int deptId) {
		return empMapper.findByDeptId(deptId);
	}

	/**
	 * 指定されたIDの従業員を取得します。
	 * @param id 従業員ID
	 * @return 従業員ドメインオブジェクト
	 */
	@Override
	public Emp findById(int id) {
		return empMapper.findById(id);
	}

	/**
	 * 従業員を新規登録します。
	 * @param emp 登録する従業員ドメインオブジェクト
	 */
	@Override
	public void save(Emp emp) {
		empMapper.save(emp);
	}

	/**
	 * 指定された社員番号が既に登録されているかをカウントします。
	 * @param Enumber 社員番号
	 * @return 該当する社員番号の件数
	 */
	@Override
	public int countByEnumber(String Enumber) {
		return empMapper.countByEnumber(Enumber);
	}

	/**
	 * 従業員情報を更新します。
	 * @param emp 更新する従業員ドメインオブジェクト
	 */
	@Override
	public void update(Emp emp) {
		empMapper.update(emp);
	}

	/**
	 * 指定された従業員を除いて、指定された社員番号が既に登録されているかをカウントします。
	 * @param enumber 社員番号
	 * @param id 除外する従業員ID
	 * @return 該当する社員番号の件数
	 */
	@Override
	public int countByEnumberExceptSelf(String enumber, int id) {
		return empMapper.countByEnumberExceptSelf(enumber, id);
	}

	/**
	 * 指定されたIDの従業員を削除します。
	 * @param id 削除する従業員ID
	 */
	@Override
	public void delete(int id) {
		empMapper.delete(id);
	}
}