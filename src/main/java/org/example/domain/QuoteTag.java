package org.example.domain;

import com.auth0.jwt.JWTVerifier;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuoteTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quote_tag_id", updatable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="quote_id")
    private Quote quote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tag_id")
    private Tag tag;


    @Builder
    public QuoteTag(Quote quote, Tag tag){
        this.quote = quote;
        this.tag = tag;
    }
    //==생성 메서드==//
    public static QuoteTag createQuoteTag(Quote quote, Tag tag){
        QuoteTag quoteTag = new QuoteTag();
        quoteTag.setQuote(quote);
        quoteTag.setTag(tag);

        return quoteTag;
    }

    //==연관관계 메서드==//
    public void setQuote(Quote quote){
        this.quote = quote;
    }

    public void setTag(Tag tag){
        this.tag = tag;
    }
}
