package io.extact.msa.spring.user.domain.model;

import jakarta.validation.Validator;

public interface ValidatorAware {
    void configureValidator(Validator validator);
}
