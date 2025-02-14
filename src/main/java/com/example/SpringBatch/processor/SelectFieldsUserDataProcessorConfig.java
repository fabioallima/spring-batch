package com.example.SpringBatch.processor;

import com.example.SpringBatch.dto.ClientDTO;
import com.example.SpringBatch.entities.Client;
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
    public ItemProcessor<ClientDTO, Client> selectFieldsUserDataProcessor() {
        return new ItemProcessor<ClientDTO, Client>() {
            @Override
            public Client process(ClientDTO clientDTO) throws Exception {
                Client client = new Client();
                client.setLogin(clientDTO.login());
                client.setName(clientDTO.name());
                client.setAvatarUrl(clientDTO.avatarUrl());

                logger.info("[PROCESSOR STEP] select client fields " + counter + " - " + client);
                counter++;

                return client;
            }
        };
    }
}
