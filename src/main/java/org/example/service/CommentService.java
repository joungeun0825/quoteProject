package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Book;
import org.example.domain.Comment;
import org.example.domain.Quote;
import org.example.domain.User;
import org.example.dto.AddCommentRequest;
import org.example.dto.AddQuoteRequest;
import org.example.repository.BookRepository;
import org.example.repository.CommentRepository;
import org.example.repository.QuoteRepository;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class CommentService {
    private final QuoteRepository quoteRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public Comment save(Long quoteId, AddCommentRequest request, User user){
        Quote quote = quoteRepository.selectQuote(quoteId)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + quoteId));;
        Comment comment = request.toEntity();
        comment.setQuote(quote);
        comment.setUser(user);

        quote.addComment(comment);
        user.addComment(comment);

        quoteRepository.save(quote);
        userRepository.save(user);
        commentRepository.save(comment);
        return comment;
    }
}
