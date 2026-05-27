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

    /**
     * 指定されたIDの従業員を取得します。
     * @param id 従業員ID
     * @return 従業員ドメインオブジェクト
     */
    Emp findById(int id);

    /**
     * 従業員を新規登録します。
     * @param emp 登録する従業員ドメインオブジェクト
     */
    void save(Emp emp);

    /**
     * 指定された社員番号が既に登録されているかをカウントします。
     * @param enumber 社員番号
     * @return 該当する社員番号の件数
     */
    int countByEnumber(String enumber);

    /**
     * 従業員情報を更新します。
     * @param emp 更新する従業員ドメインオブジェクト
     */
    void update(Emp emp);

    /**
     * 指定された従業員を除いて、指定された社員番号が既に登録されているかをカウントします。
     * @param enumber 社員番号
     * @param id 除外する従業員ID
     * @return 該当する社員番号の件数
     */
    int countByEnumberExceptSelf(String enumber, int id);

    /**
     * 指定されたIDの従業員を削除します。
     * @param id 削除する従業員ID
     */
    void delete(int id);
}