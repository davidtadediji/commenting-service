package com.example.commenting_service;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/comments")
public class CommentController {

    private final CommentService commentService;

    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable String id) {
        Optional<Comment> comment = commentService.getCommentById(id);
        return comment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@Valid @RequestBody CommentDTO commentDTO) {
       try{
        Comment comment = commentMapper.commentDTOToComment(commentDTO);
        Comment savedComment = commentService.createComment(comment);
        return ResponseEntity.ok(savedComment);}
       catch(Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
       }
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        Optional<List<Comment>> comments = commentService.getAllComments();
        return comments.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable String id) {
        try {
            Comment deletedComment = commentService.deleteComment(id);
            return ResponseEntity.ok(deletedComment);
        } catch (CommentNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable String id, @RequestBody CommentDTO commentDTO){
        Comment comment = commentMapper.commentDTOToComment(commentDTO);
        Comment updatedComment = commentService.updateComment(id, comment);
        return ResponseEntity.ok(updatedComment);
    }


}
