package io.extact.msa.spring.user.infrastructure.jpa;

import static jakarta.persistence.AccessType.*;

import jakarta.persistence.Access;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

import io.extact.msa.spring.platform.fw.domain.type.UserType;
import io.extact.msa.spring.platform.fw.infrastructure.persistence.jpa.TableEntity;
import io.extact.msa.spring.user.domain.model.AccountId;
import io.extact.msa.spring.user.domain.model.UserAccount;
import io.extact.msa.spring.user.domain.model.UserAccount.UserAccountCreatable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Access(FIELD)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
public class UserAccountEntity implements TableEntity<UserAccount>, UserAccountCreatable {

    @Id
    private Integer id;
    private String loginId;
    private String password;
    private String userName;
    private String phoneNumber;
    private String contact;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public static UserAccountEntity from(UserAccount model) {
        return new UserAccountEntity(
                model.getId().id(),
                model.getLoginId(),
                model.getPassword(),
                model.getUserName(),
                model.getPhoneNumber(),
                model.getContact(),
                model.getUserType()
                );
    }

    @Override
    public UserAccount toModel() {
        return newInstance(
                new AccountId(id),
                loginId,
                password,
                userName,
                phoneNumber,
                contact,
                userType);
    }
}
