package com.example.SpringBatch.domain;

import com.example.SpringBatch.dto.ClientDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseUser {
    private List<ClientDTO> content;

    public List<ClientDTO> getContent() {
        return content;
    }

    public void setContent(List<ClientDTO> content) {
        this.content = content;
    }
}
