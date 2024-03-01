package org.example.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberInfoDto {

    private String name;
    private String nickName;
    private String username;
    private Integer age;



    @Builder
    public MemberInfoDto(Member member) {
        this.name = member.getName();
        this.nickName = member.getNickName();
        this.username = member.getUsername();
        this.age = member.getAge();
    }
}
