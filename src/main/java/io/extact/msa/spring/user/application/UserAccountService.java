package io.extact.msa.spring.user.application;

import static io.extact.msa.rms.platform.fw.exception.BusinessFlowException.CauseType.*;

import java.util.Optional;
import java.util.function.Consumer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

import io.extact.msa.rms.platform.fw.exception.BusinessFlowException;
import io.extact.msa.rms.platform.fw.persistence.GenericRepository;
import io.extact.msa.rms.platform.fw.service.GenericService;
import io.extact.msa.spring.user.domain.UserAccountRepository;
import io.extact.msa.spring.user.domain.model.UserAccount;


@Transactional(TxType.REQUIRED)
@ApplicationScoped
public class UserAccountService implements GenericService<UserAccount> {

    private UserAccountRepository repository;

    @Inject
    public UserAccountService(UserAccountRepository userRepository) {
        this.repository = userRepository;
    }

    public Optional<UserAccount> findByLoginIdAndPasswod(String loginId, String password) {
        var u = repository.findByLoginIdAndPasswod(loginId, password);
        return Optional.ofNullable(u);
    }


    public UserAccount findByLoginId(String loginId) {
        return repository.findByLoginId(loginId);
    }

    @Override
    public Consumer<UserAccount> getDuplicateChecker() {
        return (targetUser) -> {
            var foundUser = findByLoginId(targetUser.getLoginId());
            if (foundUser != null && (targetUser.getId() == null || !foundUser.isSameId(targetUser))) {
                throw new BusinessFlowException("loginId is already registered.", DUPRICATE);
            }
        };
    }

    @Override
    public GenericRepository<UserAccount> getRepository() {
        return this.repository;
    }
}
