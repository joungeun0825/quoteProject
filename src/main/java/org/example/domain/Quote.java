package org.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quote_id", updatable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_id")
    private Book book;

    @OneToMany(mappedBy = "quote")
    private List<Comment> comments = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "quote")
    private List<QuoteTag> quoteTags = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @Column(name = "page", nullable = false)
    private Integer page;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Quote(String content, Integer page, LocalDateTime createdAt, LocalDateTime updatedAt){
        this.content = content;
        this.page = page;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    //==연관관계 메서드==//
    public void setBook(Book book){
        this.book = book;
        book.getQuotes().add(this);
    }

    public void setUser(User user){
        this.user = user;
        user.getQuotes().add(this);
    }

    public void addComment(Comment comment){
        comments.add(comment);
        comment.setQuote(this);
    }

    public void addQuoteTag(QuoteTag quoteTag){
        quoteTags.add(quoteTag);
    }

    public void update(Integer page, String content) {
        this.page = page;
        this.content = content;
    }
}
