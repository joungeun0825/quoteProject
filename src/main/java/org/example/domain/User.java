package org.example.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Long id;

    @OneToMany(mappedBy = "user")
    private List<Quote> quotes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToOne(cascade = CascadeType.PERSIST)//User를 저장할 때 관련된 RefreshToken도 함께 저장
    @JoinColumn(name = "refresh_token_id")
    private RefreshToken refreshToken;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;
/*
    @Enumerated(EnumType.STRING)
    private Role role;//권한 -> USER, ADMIN
*/
    @Builder
    public User(RefreshToken refreshToken, String username, String password, String nickname) {
        this.refreshToken = refreshToken;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public User updateUsername(String username) {
        this.username = username;

        return this;
    }
    public User updateNickName(String nickname) {
        this.nickname = nickname;

        return this;
    }
    public User updatePassword(BCryptPasswordEncoder passwordEncoder, String password) {
        this.password = passwordEncoder.encode(password);

        return this;
    }
    //==연관관계 메서드==//
    public void updateRefreshToken(RefreshToken refreshToken){
        this.refreshToken = refreshToken;
        refreshToken.setUser(this);
    }
    public void destroyRefreshToken(){
        this.refreshToken = null;
    }

    public void addQuote(Quote quote){
        quotes.add(quote);
        quote.setUser(this);
    }
    public void addComment(Comment comment){
        comments.add(comment);
        comment.setUser(this);
    }
    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }

    public boolean matchPassword(PasswordEncoder passwordEncoder, String checkPassword){
        return passwordEncoder.matches(checkPassword, getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
