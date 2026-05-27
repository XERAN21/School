package jp.kronos.ems.service;

import java.util.List;

import jp.kronos.ems.domain.Emp;
import jp.kronos.ems.form.EmpForm;

/**
 * 従業員情報に関する業務ロジックを提供するサービスインターフェースです。
 */
public interface EmpService {

	/**
	 * 指定された部署IDに所属する従業員一覧を取得します。
	 * @param deptId 部署ID
	 * @return 従業員ドメインオブジェクトのリスト
	 */
	List<Emp> getEmpsByDeptId(int deptId);

	/**
	 * 指定されたIDの従業員を取得します。
	 * @param id 従業員ID
	 * @return 従業員ドメインオブジェクト
	 */
	Emp getEmp(int id);

	/**
	 * 従業員を新規登録します。
	 * @param form 登録する従業員情報を保持するフォームオブジェクト
	 */
	void saveEmp(EmpForm form);

	/**
	 * 従業員情報を更新します。
	 * @param id 更新対象の従業員ID
	 * @param form 更新する従業員情報を保持するフォームオブジェクト
	 */
	void updateEmp(int id, EmpForm form);

	/**
	 * 指定されたIDの従業員を削除します。
	 * @param id 削除する従業員ID
	 */
	void deleteEmp(int id);
}