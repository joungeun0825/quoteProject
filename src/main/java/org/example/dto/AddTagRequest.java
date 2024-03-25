package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.domain.Tag;

@NoArgsConstructor//기본생성자
@AllArgsConstructor//모든 필드 값을 파라미터로 받는 생성자 추가
@Getter
public class AddTagRequest {
    private String tag;

    public Tag toEntity(){//생성자를 사용해 객체 생성
        return Tag.builder()
                .tag(tag)
                .build();
    }
}
