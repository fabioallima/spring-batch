package com.example.SpringBatch.reader;

import com.example.SpringBatch.domain.ResponseUser;
import com.example.SpringBatch.dto.ClientDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FetchUserDataReaderConfig implements ItemReader<ClientDTO> {

    private static Logger logger = LoggerFactory.getLogger(FetchUserDataReaderConfig.class);

    private final String BASE_URL = "http://localhost:8081";
    private RestTemplate restTemplate = new RestTemplate();

    private int page = 0;
    private List<ClientDTO> users = new ArrayList<>();
    private int userIndex = 0;

    @Value("${chunkSize}")
    private int chunkSize;

    @Value("${pageSize}")
    private int pageSize;

    @Override
    public ClientDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        ClientDTO user;
        if (userIndex < users.size()) {
            user = users.get(userIndex);
        } else {
            user = null;
        }

        userIndex++;
        return user;
    }

    private List<ClientDTO> fetchUserDataFromAPI() {
        String uri = BASE_URL + "/clients/pagedData?page=%d&size=%d";

        logger.info("[READER STEP] Fetching data ...");
        logger.info("[READER STEP] Request uri: " + String.format(uri, getPage(), pageSize));

        ResponseEntity<ResponseUser> response = restTemplate.exchange(String.format(uri, getPage(), pageSize), HttpMethod.GET, null, new ParameterizedTypeReference<ResponseUser>() {
        });

        List<ClientDTO> clientDTOList = response.getBody().getContent();
        return clientDTOList;

    }

    private int getPage() {
        return page;
    }

    private void incrementPage() {
        this.page++;
    }

    @BeforeChunk
    public void beforeChunk(ChunkContext context) {
        for (int i = 0; i < chunkSize; i += pageSize) {
            users.addAll(fetchUserDataFromAPI());
        }
    }

    @AfterChunk
    public void afterChunk(ChunkContext context) {
        logger.info("Final Chunk");

        incrementPage();
        userIndex = 0;
        users = new ArrayList<>();
    }

}
