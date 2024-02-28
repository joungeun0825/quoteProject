package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Quote;
import org.example.dto.AddQuoteRequest;
import org.example.repository.BookRepository;
import org.example.repository.QuoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.domain.Book;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final BookRepository bookRepository;

    public void save(Long bookId, AddQuoteRequest request,String writer){
        Book book = bookRepository.selectBook(bookId);
        Quote quote = request.toEntity(writer);
        quote.setBook(book);

        quoteRepository.save(quote);
    }
    @Transactional(readOnly = true)
    public List<Quote> findAll(){
        return quoteRepository.findAll();
    }
    @Transactional(readOnly = true)
    public List<Quote> findByContent(String content){
        return quoteRepository.findByContent(content);
    }
    @Transactional(readOnly = true)
    public List<Quote> findByPage(Integer page){
        return quoteRepository.findByPage(page);
    }
}
