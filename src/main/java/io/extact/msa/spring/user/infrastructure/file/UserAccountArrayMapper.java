package io.extact.msa.spring.user.infrastructure.file;

import io.extact.msa.spring.platform.fw.domain.type.UserType;
import io.extact.msa.spring.platform.fw.infrastructure.persistence.file.ModelArrayMapper;
import io.extact.msa.spring.user.domain.model.AccountId;
import io.extact.msa.spring.user.domain.model.UserAccount;
import io.extact.msa.spring.user.domain.model.UserAccount.UserAccountCreatable;

public class UserAccountArrayMapper implements ModelArrayMapper<UserAccount>, UserAccountCreatable {

    public static final UserAccountArrayMapper INSTANCE = new UserAccountArrayMapper();

    @Override
    public UserAccount toModel(String[] attributes) {

        int id = Integer.parseInt(attributes[0]);
        String loginId = attributes[1];
        String password = attributes[2];
        String userName = attributes[3];
        String phoneNumber = attributes[4];
        String contact = attributes[5];
        UserType userType = UserType.valueOf(attributes[6]);

        return newInstance(new AccountId(id), loginId, password, userName, phoneNumber, contact, userType);
    }

    @Override
    public String[] toArray(UserAccount account) {

        String[] attributes = new String[7];

        attributes[0] = String.valueOf(account.getId());
        attributes[1] = account.getLoginId();
        attributes[2] = account.getPassword();
        attributes[3] = account.getUserName();
        attributes[4] = account.getPhoneNumber();
        attributes[5] = account.getContact();
        attributes[6] = account.getUserType().name();

        return attributes;
    }
}
