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

	@Override
	public void deleteByEmpId(int empId) {
		empDetailMapper.deleteByEmpId(empId);
	}

	@Override
	public void save(EmpDetail empDetail) {
		empDetailMapper.save(empDetail);
	}
	
	@Override
	public void update(EmpDetail detail){
		empDetailMapper.update(detail);
	}
}