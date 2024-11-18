package com.example.commenting_service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
@Getter
@Setter
@RequiredArgsConstructor
public class Comment {
    @Id
    private String id;
    private String userName;
    private String message;

    @CreatedDate
    private String createdAt;
}
