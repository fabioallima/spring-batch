package com.example.SpringBatch.processor;

import com.example.SpringBatch.dto.UserDTO;
import com.example.SpringBatch.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SelectFieldsUserDataProcessorConfig {

    private static Logger logger = LoggerFactory.getLogger(SelectFieldsUserDataProcessorConfig.class);
    private int counter = 1;

    @Bean
    public ItemProcessor<UserDTO, User> selectFieldsUserDataProcessor() {
        return new ItemProcessor<UserDTO, User>() {
            @Override
            public User process(UserDTO userDTO) throws Exception {
                User user = new User();
                user.setLogin(userDTO.login());
                user.setName(userDTO.name());
                user.setAvatarUrl(userDTO.avatarUrl());

                logger.info("[PROCESSOR STEP] select user fields " + counter + " - " + user);
                counter++;

                return user;
            }
        };
    }
}
