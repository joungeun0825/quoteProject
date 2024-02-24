package org.example.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import org.example.domain.Book;
import org.example.domain.Quote;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class BookResponse {
    private Long id;
    private List<Quote> quotes = new ArrayList<>();
    private String title;
    private String author;
    private String publisher;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public BookResponse(Book book){
        this.id = book.getId();
        this.quotes = book.getQuotes();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.createdAt = book.getCreatedAt();
        this.updatedAt = book.getUpdatedAt();
    }
}
