package jp.kronos.ems.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.kronos.ems.domain.Dept;

/**
 * DEPTテーブルに対応するMyBatisのマッパーインターフェースです。
 */
@Mapper
public interface DeptMapper {
    /**
     * 全ての部署情報を取得します。
     * @return 部署ドメインオブジェクトのリスト
     */
    public List<Dept> findAll();

    /**
     * 指定されたIDに一致する部署情報を取得します。
     * @param id 部署ID
     * @return 該当する部署情報。存在しない場合はnull
     */
    public Dept findById(Integer id);

    /**
     * 部署情報を新規登録します。
     * @param dept 登録する部署ドメインオブジェクト
     */
    public void save(Dept dept);

    /**
     * 指定されたIDの部署情報を削除します。
     * @param id 削除対象の部署ID
     */
    public void delete(Integer id);

    /**
     * 部署情報を更新します。
     * @param dept 更新する部署ドメインオブジェクト
     */
    public void update(Dept dept);
}