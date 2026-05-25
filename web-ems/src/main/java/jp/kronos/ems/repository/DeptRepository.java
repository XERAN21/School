package jp.kronos.ems.repository;

import java.util.List;

import jp.kronos.ems.domain.Dept;

/**
 * 部署情報に関するデータアクセスを定義するインターフェースです。
 */
public interface DeptRepository {
	/**
	 * 全ての部署情報を取得します。
	 * @return 部署ドメインのリスト
	 */
	public List<Dept> findAll();

	/**
	 * 指定されたIDに一致する部署情報を取得します。
	 * @param id 部署ID
	 * @return 部署ドメイン
	 */
	public Dept findById(Integer id);

	/**
	 * 部署情報を新規登録します。
	 * @param dept 登録する部署ドメイン
	 */
	public void save(Dept dept);

	/**
	 * 指定されたIDの部署情報を削除します。
	 * @param id 削除対象の部署ID
	 */
	public void delete(Integer id);

	/**
	 * 部署情報を更新します。
	 * @param dept 更新する部署ドメイン
	 */
	public void update(Dept dept);
}