package io.extact.msa.spring.user.infrastructure.jpa;

import java.util.Optional;

import io.extact.msa.spring.platform.fw.infrastructure.persistence.jpa.JpaRepositoryDelegator;

public interface UserAccountJpaRepositoryDelegator extends JpaRepositoryDelegator<UserAccountEntity> {

    Optional<UserAccountEntity> findByLoginId(String loginId);
    Optional<UserAccountEntity> findByLoginIdAndPasswod(String loginId, String password);
}
