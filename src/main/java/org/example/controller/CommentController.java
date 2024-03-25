package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Book;
import org.example.domain.Comment;
import org.example.domain.Quote;
import org.example.domain.User;
import org.example.dto.AddCommentRequest;
import org.example.dto.AddQuoteRequest;
import org.example.service.BookService;
import org.example.service.CommentService;
import org.example.service.QuoteService;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@RequiredArgsConstructor
@Controller
public class CommentController {

    private final UserService userService;
    private final CommentService commentService;
    @PostMapping("/api/books/{bookId}/quotes/{quoteId}/{userId}/comment")
    public ResponseEntity<Comment> postComment(@PathVariable("bookId") Long bookId,@PathVariable("quoteId") Long quoteId, @RequestBody AddCommentRequest request, @PathVariable("userId") Long userId
    ) {
        User user = userService.findById(userId);
        Comment comment = commentService.save(quoteId, request, user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comment);

    }
}
