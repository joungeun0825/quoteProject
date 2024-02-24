package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Quote;
import org.example.dto.AddQuoteRequest;
import org.example.repository.BookRepository;
import org.example.repository.QuoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.domain.Book;

@Transactional
@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final BookRepository bookRepository;

    public void save(Long bookId, AddQuoteRequest request){
        Book book = bookRepository.selectBook(bookId);
        Quote quote = request.toEntity();
        quote.setBook(book);

        quoteRepository.save(quote);
    }
}
