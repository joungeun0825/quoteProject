package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.config.jwt.TokenProvider;
import org.example.domain.User;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        User user = refreshTokenService.findByRefreshToken(refreshToken).getUser();

        return tokenProvider.generateToken(user, Duration.ofHours(2));
    }
}
