package jp.kronos.ems.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.kronos.ems.domain.EmpDetail;

/**
 * EMP_DETAILテーブルに対応するMyBatisのマッパーインターフェースです。
 */
@Mapper
public interface EmpDetailMapper {
    /**
     * 指定された従業員IDに紐づく詳細情報を削除します。
     * @param empId 削除対象の従業員ID
     */
    public void deleteByEmpId(int empId);
    
    void save(EmpDetail emp);
    
    void update(EmpDetail detail);
    
}