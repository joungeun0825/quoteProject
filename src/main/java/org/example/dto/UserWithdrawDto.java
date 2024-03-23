package org.example.dto;

import jakarta.validation.constraints.NotBlank;

public record UserWithdrawDto(@NotBlank(message = "비밀번호를 입력해주세요")
                                String checkPassword) {
}
