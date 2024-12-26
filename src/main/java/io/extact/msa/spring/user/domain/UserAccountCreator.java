package io.extact.msa.spring.user.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import org.springframework.stereotype.Service;

import io.extact.msa.spring.platform.fw.domain.type.UserType;
import io.extact.msa.spring.user.domain.model.AccountId;
import io.extact.msa.spring.user.domain.model.UserAccount;
import io.extact.msa.spring.user.domain.model.UserAccount.UserAccountCreatable;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAccountCreator implements UserAccountCreatable {

    private final UserAccountRepository repository;
    private final Validator validator; // TODO オレオレのModelValidatorクラスとかで被せてあげるのがよい気がする

    public UserAccount create(UserAccountModelAttributes attrs) {

        AccountId id = new AccountId(repository.nextIdentity());
        UserAccount account = newInstance(
                id,
                attrs.loginId,
                attrs.password,
                attrs.userName,
                attrs.phoneNumber,
                attrs.contact,
                attrs.userType);

        Set<ConstraintViolation<UserAccount>> result = validator.validate(account);
        if (!result.isEmpty()) {
            throw new RmsConstraintViolationException("validation error.", new HashSet<>(result));
        }

        return account;
    }

    @Builder
    public static class UserAccountModelAttributes {

        private String loginId;
        private String password;
        private String userName;
        private String phoneNumber;
        private String contact;
        private UserType userType;
    }
}
