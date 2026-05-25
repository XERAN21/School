package jp.kronos.ems.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jp.kronos.ems.domain.Dept;
import jp.kronos.ems.mapper.DeptMapper;

/**
 * 部署情報のリポジトリ実装クラスです。
 */
@Repository
public class DBDeptRepository implements DeptRepository {

    private final DeptMapper deptMapper;

    /**
     * コンストラクタ。
     * @param deptMapper 部署マッパー
     */
    public DBDeptRepository(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

	@Override
	public Dept findById(Integer id) {
		return deptMapper.findById(id);
	}

	@Override
	public void save(Dept dept) {
		deptMapper.save(dept);
	}

	@Override
	public void delete(Integer id) {
		deptMapper.delete(id);
	}

	@Override
	public void update(Dept dept) {
		deptMapper.update(dept);
	}
}