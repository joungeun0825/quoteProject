package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Book;
import org.example.domain.Quote;
import org.example.dto.AddQuoteRequest;
import org.example.service.BookService;
import org.example.service.QuoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class QuoteController {
    private final QuoteService quoteService;
    private final BookService bookService;
    @PostMapping("/books/{bookId}/quotes")
    public void postQuote(@PathVariable("bookId") Long bookId, @RequestBody AddQuoteRequest request
    ) {
        quoteService.save(bookId, request);
    }
    @GetMapping("/books/{bookId}/quotes")
    public String getQuote(@PathVariable("bookId") Long bookId,
                           @RequestParam(required = false) String content,
                           @RequestParam(required = false) Integer page,
                           Model model) {
        if (null != content && null == page) {
            List<Quote> quotes = quoteService.findByContent(content);
            Book book = bookService.selectBook(bookId);
            model.addAttribute("quotes",quotes);
            model.addAttribute("book",book);

            return "quoteList";
        } else if (null == content && null != page) {
            List<Quote> quotes = quoteService.findByPage(page);
            Book book = bookService.selectBook(bookId);
            model.addAttribute("quotes",quotes);
            model.addAttribute("book",book);

            return "quoteList";
        }
        List<Quote> quotes = quoteService.findAll();
        Book book = bookService.selectBook(bookId);
        model.addAttribute("quotes",quotes);
        model.addAttribute("book",book);

        return "quoteList";
    }
}
