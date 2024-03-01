package org.example.dto;

import lombok.Builder;
import lombok.Data;
import org.example.domain.Member;

@Data
public class MemberInfoDto {

    private final String name;
    private final String nickName;
    private final String username;
    private final Integer age;


    @Builder
    public MemberInfoDto(Member member) {
        this.name = member.getName();
        this.nickName = member.getNickName();
        this.username = member.getUsername();
        this.age = member.getAge();
    }
}