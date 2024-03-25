package org.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", updatable = false)
    private Long id;

    @OneToMany(mappedBy = "tag")
    private List<QuoteTag> quoteTags = new ArrayList<>();

    private String tag;

    @Builder
    public Tag(String tag){
        this.tag = tag;
    }

    public void addQuoteTag(QuoteTag quoteTag){
        quoteTags.add(quoteTag);
    }

}
