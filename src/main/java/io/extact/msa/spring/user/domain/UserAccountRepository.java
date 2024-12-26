package io.extact.msa.spring.user.domain;

import java.util.Optional;

import io.extact.msa.spring.platform.fw.domain.service.DuplicationDataFinder;
import io.extact.msa.spring.platform.fw.infrastructure.persistence.GenericRepository;
import io.extact.msa.spring.user.domain.model.UserAccount;

public interface UserAccountRepository extends GenericRepository<UserAccount>, DuplicationDataFinder<UserAccount> {

    /**
     * ログインIDとパスワードに一致するユーザを取得。
     *
     * @param loginId ログインID
     * @param password パスワード
     * @return 該当ユーザ。該当なしはnull
     */
    Optional<UserAccount> findByLoginIdAndPasswod(String loginId, String password);
}