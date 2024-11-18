package com.example.commenting_service;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {

    @Override
    @NonNull
    Page<Comment> findAll(@NonNull Pageable pageable);
}
