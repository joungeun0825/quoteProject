package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Quote;
import org.example.dto.AddQuoteRequest;
import org.example.repository.QuoteRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;

    public Quote save(AddQuoteRequest request){
        return quoteRepository.save(request.toEntity());
    }
}
