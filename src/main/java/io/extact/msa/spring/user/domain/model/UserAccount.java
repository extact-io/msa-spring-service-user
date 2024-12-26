package io.extact.msa.spring.user.domain.model;

import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotNull;

import io.extact.msa.spring.platform.fw.domain.constraint.Contact;
import io.extact.msa.spring.platform.fw.domain.constraint.LoginId;
import io.extact.msa.spring.platform.fw.domain.constraint.Passowrd;
import io.extact.msa.spring.platform.fw.domain.constraint.PhoneNumber;
import io.extact.msa.spring.platform.fw.domain.constraint.UserName;
import io.extact.msa.spring.platform.fw.domain.constraint.UserTypeConstraint;
import io.extact.msa.spring.platform.fw.domain.model.DomainModel;
import io.extact.msa.spring.platform.fw.domain.type.UserType;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(of = "id")
@Getter
@ToString
public class UserAccount implements DomainModel, ValidatorAware {

    private final @NotNull @Valid AccountId id;
    private final @LoginId String loginId;
    private final @Passowrd String password;
    private final @UserName String userName;
    private final @PhoneNumber String phoneNumber;
    private final @Contact String contact;
    private final @UserTypeConstraint UserType userType;

    private Validator validator;


    @Override
    public void configureValidator(Validator validator) {
        this.validator = validator;
    }

    public boolean isAdmin() {
        return this.userType == UserType.ADMIN;
    }


    // コンストラクタを隠蔽するためのインターフェース
    public interface UserAccountCreatable {
        default UserAccount newInstance(AccountId id, String loginId, String password, String userName,
                String phoneNumber, String contact, UserType userType) {
            return new UserAccount(id, loginId, password, userName, phoneNumber, contact, userType);
        }
    }

}
