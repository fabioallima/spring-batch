package com.example.SpringBatch.domain;

import com.example.SpringBatch.dto.UserDTO;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseUser {
    private List<UserDTO> content;

    public List<UserDTO> getContent() {
        return content;
    }

    public void setContent(List<UserDTO> content) {
        this.content = content;
    }
}
