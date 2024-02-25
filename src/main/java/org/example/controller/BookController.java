package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Book;
import org.example.dto.AddBookRequest;
import org.example.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BookController {
    private final BookService bookService;

    @PostMapping("/books")
    public void postBook(@RequestBody AddBookRequest request) {
        bookService.save(request);
    }

    @GetMapping("/books")
    public String getBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String publisher,
            Model model) {
        if (null != title && null == author && null == publisher) {
            List<Book> books = bookService.findByTitle(title);
            model.addAttribute("books",books);

            return "bookSearch";
        } else if (null == title && null != author && null == publisher) {
            List<Book> books = bookService.findByAuthor(author);
            model.addAttribute("books",books);

            return "bookSearch";
        } else if (null == title && null == author && null != publisher) {
            List<Book> books = bookService.findByPublisher(publisher);
            model.addAttribute("books",books);

            return "bookSearch";
        }
        List<Book> books = bookService.findAll();
        model.addAttribute("books",books);

        return "bookSearch";
    }


    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> selectBook(@PathVariable("bookId") Long bookId){
        Book book = bookService.selectBook(bookId);
        return ResponseEntity.ok()
                .body(book);
    }
}
