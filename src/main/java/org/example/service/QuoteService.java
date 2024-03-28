package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.*;
import org.example.dto.AddQuoteRequest;
import org.example.repository.BookRepository;
import org.example.repository.QuoteRepository;
import org.example.repository.QuoteTagRepository;
import org.example.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final QuoteTagRepository quoteTagRepository;

    public Quote save(Long bookId, AddQuoteRequest request, User user, List<Tag> tags){
        Book book = bookRepository.selectBook(bookId);
        Quote quote = request.toEntity();
        quote.setBook(book);
        quote.setUser(user);

        for (Tag tag : tags) {
            QuoteTag quoteTag = QuoteTag.createQuoteTag(quote,tag);
            quoteTagRepository.save(quoteTag);
            tag.addQuoteTag(quoteTag);
            quote.addQuoteTag(quoteTag);
        }

        book.addQuote(quote);
        user.addQuote(quote);

        bookRepository.save(book);
        userRepository.save(user);
        quoteRepository.save(quote);

        return quote;
    }
    public Quote update(Long quoteId, AddQuoteRequest request){
        Quote quote = quoteRepository.selectQuote(quoteId)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + quoteId));
        authorizeArticleAuthor(quote);
        quote.update(request.getPage(), request.getContent());

        return quote;
    }
    public void delete(Long quoteId) {
        Quote quote = quoteRepository.selectQuote(quoteId)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + quoteId));
        authorizeArticleAuthor(quote);
        quoteRepository.delete(quoteId);
    }
    @Transactional(readOnly = true)
    public List<Quote> findAll(Book book){
        return quoteRepository.findAll(book);
    }
    @Transactional(readOnly = true)
    public List<Quote> findAllQuotes(){
        return quoteRepository.findAllQuotes();
    }
    @Transactional(readOnly = true)
    public List<Quote> findByContent(String content,Book book){
        return quoteRepository.findByContent(content,book);
    }
    @Transactional(readOnly = true)
    public List<Quote> findByPage(Integer page,Book book){
        return quoteRepository.findByPage(page,book);
    }
    @Transactional(readOnly = true)
    public Quote selectQuote(Long quoteId){
        return quoteRepository.selectQuote(quoteId).orElseThrow(() -> new IllegalArgumentException("not found : " + quoteId));
    }
    // 게시글을 작성한 유저인지 확인
    private static void authorizeArticleAuthor(Quote quote) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!quote.getUser().getUsername().equals(userName)) {
            throw new IllegalArgumentException(userName);
        }
    }
}
