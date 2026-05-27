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

    /**
     * 従業員詳細情報を新規登録します。
     * @param emp 登録する従業員詳細ドメインオブジェクト
     */
    void save(EmpDetail emp);

    /**
     * 従業員詳細情報を更新します。
     * @param detail 更新する従業員詳細ドメインオブジェクト
     */
    void update(EmpDetail detail);

}