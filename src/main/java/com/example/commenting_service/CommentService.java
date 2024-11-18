package com.example.commenting_service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment createComment(Comment comment) {
        try {
            return commentRepository.save(comment);
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to save comment", e);
        }
    }

    public Optional<List<Comment>> getAllComments() {
        return Optional.of(commentRepository.findAll());
    }

    public Optional<Comment> getCommentById(String id) {
        return commentRepository.findById(id);
    }

    public Comment updateComment(String id, Comment comment) {
        if (commentRepository.existsById(id)) {
            comment.setId(id);
            return commentRepository.save(comment);
        } else {
            return null;
        }
    }

    public Comment deleteComment(String id) {
        Comment commentToDelete = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment with id " + id + "not found!"));

        commentRepository.delete(commentToDelete);
        return commentToDelete;
    }
}
