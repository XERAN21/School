package jp.kronos.ems.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.kronos.ems.domain.Emp;

/**
 * EMPテーブルに対応するMyBatisのマッパーインターフェースです。
 */
@Mapper
public interface EmpMapper {

    /**
     * 指定された部署IDに所属する従業員を全て削除します。
     * @param deptId 部署ID
     */
    void deleteByDeptId(int deptId);
    
    /**
     * 指定された部署IDに所属する従業員一覧を取得します。
     * @param deptId 部署ID
     * @return 従業員ドメインオブジェクトのリスト
     */
    List<Emp> findByDeptId(int deptId);
    
}