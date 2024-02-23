package org.example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quote {
    @Id
    @GeneratedValue
    @Column(name = "quote_id", updatable = false)
    private Long id;

    @Column(name = "content", nullable = false)
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
    public Quote(String content, Integer page){
        this.content = content;
        this.page = page;
    }

}
