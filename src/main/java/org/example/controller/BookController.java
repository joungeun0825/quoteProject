package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Book;
import org.example.domain.RefreshToken;
import org.example.dto.AddBookRequest;
import org.example.service.BookService;
import org.example.service.RefreshTokenService;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BookController {
    private final BookService bookService;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    @PostMapping("/books")
    public void postBook(@RequestBody AddBookRequest request) {
        bookService.save(request);
    }

    @GetMapping("/books")
    public String getBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String publisher,
            @CookieValue(name = "refresh_token", required = false) String refreshToken,
            Model model) {
        boolean loggedIn = false;

        if (refreshToken != null) {
            RefreshToken refreshTokenEntity = refreshTokenService.findByRefreshToken(refreshToken);
            if (refreshTokenEntity != null) {
                Long refreshTokenId = refreshTokenEntity.getId();
                userService.findByRefreshTokenId(refreshTokenId);
                // 사용자 정보가 정상적으로 조회되었을 때
                loggedIn = true; // 로그인 상태로 변경
            } else {
                // refreshToken이 존재하지 않는 경우
                loggedIn = false; // 로그아웃 상태로 유지
            }
        }

        List<Book> books;
        if (title != null && author == null && publisher == null) {
            books = bookService.findByTitle(title);
        } else if (title == null && author != null && publisher == null) {
            books = bookService.findByAuthor(author);
        } else if (title == null && author == null && publisher != null) {
            books = bookService.findByPublisher(publisher);
        } else {
            books = bookService.findAll();
        }

        model.addAttribute("loggedIn", loggedIn);
        model.addAttribute("books", books);
        return "bookSearch";
    }




}
