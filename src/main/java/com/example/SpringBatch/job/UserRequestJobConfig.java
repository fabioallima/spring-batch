package com.example.SpringBatch.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRequestJobConfig {

    private static Logger logger = LoggerFactory.getLogger(UserRequestJobConfig.class);

    @Bean
    public Job userRequestJob(Step fetchUserDataAndStoreDBStep, JobRepository jobRepository) {

        logger.info("Start job execution ...");

        return new JobBuilder("userRequestJob", jobRepository)
                .start(fetchUserDataAndStoreDBStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
