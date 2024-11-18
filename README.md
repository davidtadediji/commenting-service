# Commenting Microservice

## Overview

This microservice allows you to manage comments in a MongoDB database through a RESTful API. It provides basic validation, exception handling, MongoDB auditing support, and a structured mechanism for mapping data transfer objects (DTOs) to entities.

## Functionalities

### 1. MongoDB Configuration for Auditing
- **MongoConfig**: Configures MongoDB auditing for automatic timestamp generation using the `@CreatedDate` annotation. This ensures that when a comment is created, a creation timestamp (`createdAt`) is automatically set by MongoDB.

### 2. Comment Service
- **createComment(Comment comment)**: Handles the creation of a new comment by saving it to the database.
- **getAllComments()**: Retrieves all comments stored in the database.
- **getCommentById(String id)**: Retrieves a single comment by its ID from the database.
- **updateComment(String id, Comment comment)**: Updates an existing comment by its ID if it exists in the database.
- **deleteComment(String id)**: Deletes a comment by its ID if it exists. If the comment is not found, it throws a `CommentNotFoundException`.

### 3. Comment Repository
- **CommentRepository**: A MongoDB repository that provides CRUD operations for `Comment` entities (e.g., saving, finding, and deleting comments).

### 4. Custom Exception Handling
- **CommentNotFoundException**: Custom exception thrown when a comment is not found during operations like delete or update.

### 5. Comment Data Transfer Object (DTO)
- **CommentDTO**: A data transfer object used to transfer comment data between the client and the server. It includes validation annotations (`@NotBlank`) for fields like `userName` and `message` to ensure they are not empty when creating or updating a comment.

### 6. Comment Controller (REST API)
Exposes the following REST endpoints:
- **GET /api/comments/{id}**: Retrieves a comment by its ID. If found, returns the comment; otherwise, returns a 404 Not Found response.
- **POST /api/comments**: Creates a new comment. The request body must contain a `CommentDTO`. Returns the created comment in the response body.
- **GET /api/comments**: Retrieves a list of all comments. If comments exist, returns them in the response body; otherwise, returns a 404 Not Found response.
- **DELETE /api/comments/{id}**: Deletes a comment by its ID. If successful, returns the deleted comment in the response body; otherwise, returns a 404 Not Found response.
- **PUT /api/comments/{id}**: Updates a comment by its ID. The request body must contain a `CommentDTO`. If successful, returns the updated comment in the response body.

### 7. Global Exception Handling
- **GlobalExceptionHandler**: Handles validation errors globally using the `MethodArgumentNotValidException`. When validation fails, a 400 Bad Request response is returned with a map of field names and error messages.
- **CommentNotFoundException**: Used for handling cases where a comment is not found for operations like delete or update, returning a 404 Not Found response.

### 8. Comment Mapper (using MapStruct)
- **CommentMapper**: A MapStruct-based interface that maps between `CommentDTO` and `Comment` entities. It converts the data transfer object (DTO) to the `Comment` entity and vice versa.

### 9. MongoDB Document for Comment
- **Comment**: Represents the `Comment` entity stored in MongoDB. It contains:
   - `id`: Unique identifier for the comment (MongoDB document ID).
   - `userName`: Name of the user who made the comment.
   - `message`: The content of the comment.
   - `createdAt`: Timestamp when the comment was created (automatically set by MongoDB).

## Summary of Functionalities

### CRUD Operations:
- **Create** a new comment.
- **Retrieve** all comments or a specific comment by ID.
- **Update** an existing comment by its ID.
- **Delete** a comment by its ID.

### Validation:
- The `CommentDTO` ensures that the `userName` and `message` fields are non-blank when creating or updating a comment.

### Exception Handling:
- The `GlobalExceptionHandler` handles validation errors globally, and `CommentNotFoundException` is used for handling cases where a comment is not found for operations like delete or update.

### MongoDB Auditing:
- Automatic creation timestamp (`createdAt`) is added when a comment is saved to the database, using MongoDB's auditing capabilities.

### Mapping:
- `CommentMapper` converts between the `CommentDTO` and the `Comment` entity to ensure proper data transfer between the client and server.

## Conclusion

This microservice allows you to manage comments in a MongoDB database, providing a robust API for CRUD operations, validation, and error handling, with built-in MongoDB auditing and data transfer object mapping.


## Upcoming Features

We are actively working on the following enhancements to make the commenting microservice more robust, scalable, and feature-rich:

### 1. **Pagination and Sorting**
- Fetch comments with pagination and sorting capabilities to efficiently manage large datasets.

### 2. **User Authentication and Authorization**
- Integration with Spring Security for user authentication.
- Role-based access control (RBAC) to manage permissions for creating, updating, and deleting comments.

### 3. **Comment Moderation**
- Flagging inappropriate comments for review.
- Admin can approve or reject flagged comments.

### 4. **Nested Comments (Threaded Comments)**
- Support for threaded replies to comments, creating a hierarchical structure of discussions.

### 5. **Audit Logging**
- Tracking sensitive operations such as comment creation, updates, and deletions with audit logs.

### 6. **Rate Limiting**
- Implement rate limiting to prevent abuse and control how many comments a user can post in a given time period.

### 7. **Search Functionality**
- Search for comments by content, username, or other criteria using full-text search capabilities.

### 8. **Email or Notification System**
- Email or push notifications when a comment is replied to or liked.
- Webhooks for notifying external systems about new comments.

### 9. **Like or Dislike Comments**
- Users can like or dislike comments, enabling a voting system for better engagement.

### 10. **Data Validation and Error Handling**
- Improved data validation for comment length, forbidden words, and spam prevention.
- Extended global exception handling for various scenarios like `DataIntegrityViolationException` or `AccessDeniedException`.

### 11. **Caching**
- Implement caching to improve performance by storing popular or frequently accessed comments in memory.

### 12. **Versioning**
- API versioning to maintain backward compatibility while evolving the service.

### 13. **Metrics and Monitoring**
- Integration with Prometheus and Grafana for monitoring system health, performance, and anomaly detection.

### 14. **Data Backup and Archiving**
- Archiving old comments for long-term storage while reducing the load on the primary database.

## Contributing

We welcome contributions to improve the commenting service. If you'd like to add new features or fix bugs, feel free to submit a pull request!

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
