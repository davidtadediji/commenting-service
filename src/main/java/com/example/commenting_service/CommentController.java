package com.example.commenting_service;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/comments")
public class CommentController {

    private final CommentService commentService;

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    public CommentController(CommentService commentService, CommentMapper commentMapper, CommentRepository commentRepository) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable String id) {
        Optional<Comment> comment = commentService.getCommentById(id);
        return comment.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@Valid @RequestBody CommentDTO commentDTO) {
        try {
            Comment comment = commentMapper.commentDTOToComment(commentDTO);
            Comment savedComment = commentService.createComment(comment);
            return ResponseEntity.ok(savedComment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Comment>> getAllComments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String order
    ) {
        Sort sort = order.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        return ResponseEntity.ok(commentRepository.findAll(PageRequest.of(page, size, sort)));
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
    public ResponseEntity<Comment> updateComment(@PathVariable String id, @RequestBody CommentDTO commentDTO) {
        Comment comment = commentMapper.commentDTOToComment(commentDTO);
        Comment updatedComment = commentService.updateComment(id, comment);
        return ResponseEntity.ok(updatedComment);
    }


}
