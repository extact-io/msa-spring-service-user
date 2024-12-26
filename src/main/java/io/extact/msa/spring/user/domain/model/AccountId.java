package io.extact.msa.spring.user.domain.model;

import io.extact.msa.spring.platform.fw.domain.constraint.RmsId;
import io.extact.msa.spring.platform.fw.domain.model.Identity;

public record AccountId(
        @RmsId int id) implements Identity {
}
