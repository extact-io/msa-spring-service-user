package io.extact.msa.spring.user.infrastructure.jpa;

import java.util.Optional;

import io.extact.msa.spring.platform.fw.infrastructure.ModelEntityMapper;
import io.extact.msa.spring.platform.fw.infrastructure.persistence.jpa.AbstractJpaRepository;
import io.extact.msa.spring.user.domain.UserAccountRepository;
import io.extact.msa.spring.user.domain.model.UserAccount;

public class UserAccountJpaRepository extends AbstractJpaRepository<UserAccount, UserAccountEntity>
        implements UserAccountRepository {

    private final UserAccountJpaRepositoryDelegator delegator;
    private final ModelEntityMapper<UserAccount, UserAccountEntity> entityMapper;

    public UserAccountJpaRepository(UserAccountJpaRepositoryDelegator delegator,
            ModelEntityMapper<UserAccount, UserAccountEntity> entityMapper) {
        super(delegator, entityMapper);
        this.delegator = delegator;
        this.entityMapper = entityMapper;
    }

    @Override
    public Optional<UserAccount> findDuplicationData(UserAccount checkModel) {
        return delegator.findByLoginId(checkModel.getLoginId())
                .map(entityMapper::toModel);
    }

    @Override
    public Optional<UserAccount> findByLoginIdAndPasswod(String loginId, String password) {
        return delegator.findByLoginIdAndPasswod(loginId, password)
                .map(entityMapper::toModel);
    }
}
