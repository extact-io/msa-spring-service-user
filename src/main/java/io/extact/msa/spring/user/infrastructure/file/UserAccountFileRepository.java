package io.extact.msa.spring.user.infrastructure.file;

import java.util.Optional;

import io.extact.msa.spring.platform.fw.infrastructure.persistence.file.AbstractFileRepository;
import io.extact.msa.spring.platform.fw.infrastructure.persistence.file.ModelArrayMapper;
import io.extact.msa.spring.platform.fw.infrastructure.persistence.file.io.FileOperator;
import io.extact.msa.spring.user.domain.UserAccountRepository;
import io.extact.msa.spring.user.domain.model.UserAccount;

public class UserAccountFileRepository extends AbstractFileRepository<UserAccount> implements UserAccountRepository {

    static final String FILE_ENTITY = "user-account";

    public UserAccountFileRepository(FileOperator fileReadWriter, ModelArrayMapper<UserAccount> mapper) {
        super(fileReadWriter, mapper);
    }

    @Override
    public String getEntityName() {
        return FILE_ENTITY;
    }

    @Override
    public Optional<UserAccount> findDuplicationData(UserAccount checkModel) {
        return this.findAll().stream()
                .filter(account -> account.getLoginId().equals(checkModel.getLoginId()))
                .findFirst();
    }

    @Override
    public Optional<UserAccount> findByLoginIdAndPasswod(String loginId, String password) {
        return this.findAll().stream()
                .filter(account -> account.getLoginId().equals(loginId))
                .filter(account -> account.getPassword().equals(password))
                .findFirst();
    }
}
