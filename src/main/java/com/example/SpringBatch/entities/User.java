package com.example.SpringBatch.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    private String login;
    private String name;
    private String avatarUrl;
}
