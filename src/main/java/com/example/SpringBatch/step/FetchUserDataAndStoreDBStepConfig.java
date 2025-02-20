package com.example.SpringBatch.step;

import com.example.SpringBatch.dto.ClientDTO;
import com.example.SpringBatch.entities.Client;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class FetchUserDataAndStoreDBStepConfig {

    @Autowired
    @Qualifier("transactionManagerApp")
    private PlatformTransactionManager transactionManager;

    @Value("${chunkSize}")
    private int chunkSize;

    @Bean
    public Step fetchUserDataAndStoreDBStep(ItemReader<ClientDTO> fetchUserDataReader,
                                            ItemProcessor<ClientDTO, Client> selectFieldsUserDataProcessor,
                                            ItemWriter<Client> insertUserDataDBWriter,
                                            JobRepository jobRepository) {
        return new StepBuilder("fetchUserDataAndStoreDBStep", jobRepository)
                .<ClientDTO, Client>chunk(chunkSize, transactionManager)
                .reader(fetchUserDataReader)
                .processor(selectFieldsUserDataProcessor)
                .writer(insertUserDataDBWriter)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(10)
                .retry(Exception.class)
                .retryLimit(3)
                .noRollback(Exception.class)
                .build();
    }
}
