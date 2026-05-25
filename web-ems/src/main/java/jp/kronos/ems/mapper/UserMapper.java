package jp.kronos.ems.mapper;

import org.apache.ibatis.annotations.Mapper;

import jp.kronos.ems.domain.User;

/**
 * USERテーブルに対応するMyBatisのマッパーインターフェースです。
 */
@Mapper
public interface UserMapper {
    /**
     * ユーザーIDとパスワードを条件にユーザー情報を取得します（ログイン認証用）。
     * @param userId ユーザーID
     * @param password パスワード
     * @return 該当するユーザー情報。存在しない場合はnull
     */
    User findByUserIdAndPassword(String userId, String password);

}