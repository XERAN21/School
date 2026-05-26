package jp.kronos.ems.repository;

import java.util.List;

import jp.kronos.ems.domain.Emp;

/**
 * 従業員情報に関するデータアクセスを定義するインターフェースです。
 */
public interface EmpRepository {
	/**
	 * 指定された部署IDに所属する従業員を削除します。
	 * @param deptId 部署ID
	 */
	void deleteByDeptId(int deptId);

	/**
	 * 指定された部署IDに所属する従業員一覧を取得します。
	 * @param deptId 部署ID
	 * @return 従業員ドメインのリスト
	 */
	List<Emp> findByDeptId(int deptId);
	
	Emp findById(int id);
	
	void save(Emp emp);
	
	int countByEnumber(String Enumber);
	
	void update(Emp emp);
	
    int countByEnumberExceptSelf(String enumber, int id);
}