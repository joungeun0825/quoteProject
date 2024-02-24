package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Book;
import org.example.domain.Quote;
import org.example.dto.BookResponse;
import org.example.repository.BookSearch;
import org.example.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<BookResponse>> getBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String publisher) {
        if (null != title && null == author && null == publisher) {
            List<BookResponse> books = bookService.findByTitle(title)
                    .stream()
                    .map(BookResponse::new)
                    .toList();
            return ResponseEntity.ok()
                    .body(books);
        } else if (null == title && null != author && null == publisher) {
            List<BookResponse> books = bookService.findByAuthor(author)
                    .stream()
                    .map(BookResponse::new)
                    .toList();
            return ResponseEntity.ok()
                    .body(books);
        } else if (null == title && null == author && null != publisher) {
            List<BookResponse> books = bookService.findByPublisher(publisher)
                    .stream()
                    .map(BookResponse::new)
                    .toList();
            return ResponseEntity.ok()
                    .body(books);
        }
        List<BookResponse> books = bookService.findAll()
                .stream()
                .map(BookResponse::new)
                .toList();
        return ResponseEntity.ok()
                .body(books);
    }
}
