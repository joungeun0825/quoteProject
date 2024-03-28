package org.example.service;


import lombok.RequiredArgsConstructor;
import org.example.domain.Quote;
import org.example.domain.QuoteTag;
import org.example.domain.Tag;
import org.example.repository.QuoteRepository;
import org.example.repository.QuoteTagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QuoteTagService {

    private final QuoteTagRepository quoteTagRepository;
    private final QuoteRepository quoteRepository;


    @Transactional(readOnly = true)
    public QuoteTag selectTag(Long tagId){
        return quoteTagRepository.selectQuoteTag(tagId).orElseThrow(() -> new IllegalArgumentException("not found : " + tagId));
    }

    @Transactional(readOnly = true)
    public List<QuoteTag> selectTags(List<Long> quoteTagIds){
        List<QuoteTag> tags = new ArrayList<>();
        for (Long quoteTagId : quoteTagIds) {
            QuoteTag tag = quoteTagRepository.selectQuoteTag(quoteTagId).orElseThrow(() -> new IllegalArgumentException("not found : " + quoteTagId));
            tags.add(tag);
        }
        return tags;
    }

    @Transactional(readOnly = true)
    public List<QuoteTag> selectAllTag(){
        return quoteTagRepository.selectAllTag().orElseThrow(() -> new IllegalArgumentException("not found"));
    }
    @Transactional(readOnly = true)
    public List<Quote> selectQuotes(Tag randomTag){
        Long randomTagId = randomTag.getId();
        List<QuoteTag> quoteTags = quoteTagRepository.selectQuotes(randomTagId).orElseThrow(() -> new IllegalArgumentException("not found"));

        List<Quote> quotes = new ArrayList<>();

        for (QuoteTag quoteTag : quoteTags) {
            Long quoteId = quoteTag.getQuote().getId();
            Quote quote = quoteRepository.selectQuote(quoteId).orElseThrow(() -> new IllegalArgumentException("not found : " + quoteId));
            quotes.add(quote);
        }
        return quotes;
    }
}
