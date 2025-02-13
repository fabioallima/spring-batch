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
public class JobConfig {

    private static Logger logger = LoggerFactory.getLogger(JobConfig.class);

    @Bean
    public Job job(Step fetchUserDataAndStoreDBStep, JobRepository jobRepository) {

        logger.info("Start job execution ...");

        return new JobBuilder("job", jobRepository)
                .start(fetchUserDataAndStoreDBStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
