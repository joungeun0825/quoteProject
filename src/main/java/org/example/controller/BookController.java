package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Book;
import org.example.domain.Quote;
import org.example.dto.AddBookRequest;
import org.example.dto.AddQuoteRequest;
import org.example.dto.BookResponse;
import org.example.service.BookService;
import org.example.service.QuoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BookController {
    private final BookService bookService;

    @PostMapping("/books")
    public void postBook(@RequestBody AddBookRequest request) {
        bookService.save(request);
    }
    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> selectBook(@PathVariable("bookId") Long bookId){
        Book book = bookService.selectBook(bookId);
        return ResponseEntity.ok()
                .body(book);
    }
}
