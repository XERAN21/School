package jp.kronos.ems.repository;

import org.springframework.stereotype.Repository;

import jp.kronos.ems.domain.EmpDetail;
import jp.kronos.ems.mapper.EmpDetailMapper;

/**
 * 従業員詳細情報のリポジトリ実装クラスです。
 * MyBatisのMapperを使用してDB操作を行います。
 */
@Repository
public class DBEmpDetailRepository implements EmpDetailRepository {

	private final EmpDetailMapper empDetailMapper;

	/**
	 * コンストラクタ。
	 * @param empDetailMapper 従業員詳細マッパー
	 */
	public DBEmpDetailRepository(EmpDetailMapper empDetailMapper) {
		this.empDetailMapper = empDetailMapper;
	}

	/**
	 * 指定された従業員IDに紐づく詳細情報を削除します。
	 * @param empId 削除対象の従業員ID
	 */
	@Override
	public void deleteByEmpId(int empId) {
		empDetailMapper.deleteByEmpId(empId);
	}

	/**
	 * 従業員詳細情報を新規登録します。
	 * @param empDetail 登録する従業員詳細ドメインオブジェクト
	 */
	@Override
	public void save(EmpDetail empDetail) {
		empDetailMapper.save(empDetail);
	}

	/**
	 * 従業員詳細情報を更新します。
	 * @param detail 更新する従業員詳細ドメインオブジェクト
	 */
	@Override
	public void update(EmpDetail detail){
		empDetailMapper.update(detail);
	}
}