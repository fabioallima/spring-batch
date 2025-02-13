package com.example.SpringBatch.writer;

import com.example.SpringBatch.dto.UserDTO;
import com.example.SpringBatch.entities.User;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertUserDataDBWriterConfig {

    @Bean
    public ItemWriter<User> insertUserDataDBWriter() {
        return users -> {
            users.forEach(System.out::println);
        };
    }
}
