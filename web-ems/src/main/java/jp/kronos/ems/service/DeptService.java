package jp.kronos.ems.service;

import java.util.List;

import jp.kronos.ems.domain.Dept;
import jp.kronos.ems.form.DeptForm;

/**
 * 部署管理に関する業務ロジックを定義するインターフェースです。
 */
public interface DeptService {
	/**
	 * 全ての部署情報を取得します。
	 * @return 部署のリスト
	 */
	public List<Dept> getDepts();

	/**
	 * 指定されたIDの部署情報を取得します。
	 * @param id 部署ID
	 * @return 部署情報
	 */
	public Dept getDept(Integer id);

	/**
	 * 部署を新規作成します。
	 * @param form 部署登録用フォーム
	 */
	public void createDept(DeptForm form);

	/**
	 * 指定された部署を削除します。
	 * 削除対象部署に所属する従業員と従業員詳細も同時に削除します。
	 * @param id 部署ID
	 */
	public void deleteDept(Integer id);

	/**
	 * 部署情報を編集・更新します。
	 * @param form 部署編集用フォーム
	 * @param id 部署ID
	 */
	public void editDept(DeptForm form, int id);

}