package org.example.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.example.domain.Quote;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserResponse {
    private Long id;

    private String email;

    private String password;

    private String nickname;

    private List<Quote> quotes = new ArrayList<>();
}
