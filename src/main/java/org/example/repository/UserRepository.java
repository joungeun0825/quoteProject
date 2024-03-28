package org.example.repository;

import org.example.domain.RefreshToken;
import org.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByRefreshTokenId(Long refreshTokenId);

    Optional<User> findByRefreshToken(String refreshToken);
    boolean existsByUsername(String email);
}
