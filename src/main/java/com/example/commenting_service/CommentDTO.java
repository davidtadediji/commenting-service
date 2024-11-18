package com.example.commenting_service;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@RequiredArgsConstructor
public class CommentDTO {
    private String id;

    @NotBlank
    private String userName;

    @NotBlank
    private String message;

    private String createdAt;
}
