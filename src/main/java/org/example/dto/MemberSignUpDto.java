package org.example.dto;

import org.example.domain.Member;

public record MemberSignUpDto(String username, String password, String name, String nickName, Integer age) {

    public Member toEntity() {
        return Member.builder().username(username).password(password).name(name).nickName(nickName).age(age).build();
    }
}