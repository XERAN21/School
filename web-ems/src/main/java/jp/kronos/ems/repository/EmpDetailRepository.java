package jp.kronos.ems.repository;

/**
 * 従業員詳細情報に関するデータアクセスを定義するインターフェースです。
 */
public interface EmpDetailRepository {

	/**
	 * 指定された従業員IDに紐づく詳細情報を削除します。
	 * @param empId 従業員ID
	 */
	void deleteByEmpId(int empId);
}