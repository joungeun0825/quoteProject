package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Quote;
import org.example.dto.AddQuoteRequest;
import org.example.service.QuoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class QuoteController {

    private final QuoteService quoteService;

    @PostMapping("/quotes")
    public void postQuote(@RequestParam("bookId") Long bookId, @RequestBody AddQuoteRequest request) {
        quoteService.save(bookId, request);
    }
}
