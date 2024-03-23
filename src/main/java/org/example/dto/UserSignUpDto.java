package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.example.domain.User;

public record UserSignUpDto(@NotBlank(message = "아이디를 입력해주세요") @Size(min = 7, max = 25, message = "아이디는 7~25자 내외로 입력해주세요")
String username,

                            @NotBlank(message = "비밀번호를 입력해주세요")
                            @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
                                    message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
                            String password,
                            @NotBlank(message = "닉네임을 입력해주세요.")
                            @Size(min=2, message = "닉네임이 너무 짧습니다.")
                            @NotBlank String nickname){
    public User toEntity() {
        return User.builder().nickname(nickname).password(password).username(username).build();
    }
}