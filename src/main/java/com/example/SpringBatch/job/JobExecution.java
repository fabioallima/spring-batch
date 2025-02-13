package com.example.SpringBatch.job;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobExecution {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job helloWorldJob;

    @Autowired
    private Job userRequestJob;

    private static Logger logger = LoggerFactory.getLogger(JobExecution.class);

    public void executUserRequestJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("userRequestJob", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        jobLauncher.run(userRequestJob, jobParameters);
    }

    public void executHelloWorldJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("helloWorldJob", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        jobLauncher.run(helloWorldJob, jobParameters);
    }

    @PostConstruct
    public void runJobOnStartup() {
        try {
            executUserRequestJob();
        } catch (Exception e) {
            logger.error("Erro ao executar o job: {}", e.getMessage());
        }
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void runJobScheduled() {
        try {
            executHelloWorldJob();
        } catch (Exception e) {
            logger.error("Erro ao executar o job: {}", e.getMessage());
        }
    }
}
