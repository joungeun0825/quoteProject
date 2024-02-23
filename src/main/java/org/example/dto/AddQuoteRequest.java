package org.example.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.Quote;

@NoArgsConstructor//기본생성자
@AllArgsConstructor//모든 필드 값을 파라미터로 받는 생성자 추가
@Getter
public class AddQuoteRequest {
    private String content;
    private Integer page;

    public Quote toEntity(){//생성자를 사용해 객체 생성
        return Quote.builder()
                .content(content)
                .page(page)
                .build();
    }
}
