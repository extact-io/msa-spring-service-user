package io.extact.msa.spring.user.infrastructure.file;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import io.extact.msa.spring.platform.fw.domain.constraint.ValidationConfiguration;
import io.extact.msa.spring.platform.fw.infrastructure.persistence.file.ModelArrayMapper;
import io.extact.msa.spring.platform.fw.infrastructure.persistence.file.io.FileOperator;
import io.extact.msa.spring.platform.fw.infrastructure.persistence.file.io.LoadPathDeriver;
import io.extact.msa.spring.user.domain.model.UserAccount;

@Configuration(proxyBeanMethods = false)
@Import(ValidationConfiguration.class)
@Profile("file")
class UserAccountFileRepositoryConfig {

    @Bean
    ModelArrayMapper<UserAccount> userAccountArrayMapper() {
        return UserAccountArrayMapper.INSTANCE;
    }

    @Bean
    UserAccountFileRepository userAccountFileRepository(Environment env, ModelArrayMapper<UserAccount> mapper) {
        LoadPathDeriver pathDeriver = new LoadPathDeriver(env);
        FileOperator fileOperator = new FileOperator(pathDeriver.derive(UserAccountFileRepository.FILE_ENTITY));
        return new UserAccountFileRepository(fileOperator, mapper);
    }
}
