package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.*;
import org.example.dto.AddQuoteRequest;
import org.example.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class QuoteController {
    private final QuoteService quoteService;
    private final BookService bookService;
    private final UserService userService;
    private final QuoteTagService quoteTagService;
    private final TagService tagService;
    private final RefreshTokenService refreshTokenService;
    @PostMapping("/api/books/{bookId}/quotes/{userId}")
    public ResponseEntity<Quote> postQuote(@PathVariable("bookId") Long bookId,
                                           @RequestBody AddQuoteRequest request,
                                           @PathVariable("userId") Long userId,
                                           @RequestParam List<Long> tagId
    ) {
        User user = userService.findById(userId);
        List<Tag> tags = tagService.selectTags(tagId);
        Quote quote = quoteService.save(bookId, request, user, tags);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(quote);

    }
    @PutMapping("/api/books/{bookId}/quotes/{quoteId}")
    public ResponseEntity<Quote> updateQuote(@RequestBody AddQuoteRequest request,@PathVariable("quoteId") Long quoteId
    ) {
        Quote quote = quoteService.update(quoteId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(quote);

    }
    @DeleteMapping("/api/books/{bookId}/quotes/{quoteId}")
    public ResponseEntity<Void> deleteQuote(@PathVariable("quoteId") Long quoteId) {
        quoteService.delete(quoteId);

        return ResponseEntity.ok()
                .build();

    }
    //카테고리로 특정 책에서 책갈피 찾기
    @GetMapping("/books/{bookId}/quotes")
    public String getQuote(@PathVariable("bookId") Long bookId,
                           @RequestParam(required = false) String content,
                           @RequestParam(required = false) Integer page,
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

        model.addAttribute("loggedIn", loggedIn);

        Book book = bookService.selectBook(bookId);
        List<Quote> quotes;

        if (content != null && page == null) {
            quotes = quoteService.findByContent(content, book);
        } else if (content == null && page != null) {
            quotes = quoteService.findByPage(page, book);
        } else {
            quotes = quoteService.findAll(book);
        }

        model.addAttribute("quotes", quotes);
        model.addAttribute("book", book);

        return "quoteList";
    }

    @GetMapping("/books/{bookId}/quotes/create")
    public String createQuote(@PathVariable("bookId") Long bookId,
                              @CookieValue(name = "refresh_token", required = false) String refreshToken,
                              Model model){
        boolean loggedIn = false; // 기본적으로 로그아웃 상태로 설정

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

        // 로그인 상태를 모델에 추가
        model.addAttribute("loggedIn", loggedIn);

        // 로그인되어 있을 때
        if(loggedIn){
            try {
                Long refreshTokenId = refreshTokenService.findByRefreshToken(refreshToken).getId();
                User user = userService.findByRefreshTokenId(refreshTokenId);
                Book book = bookService.selectBook(bookId);
                List<QuoteTag> tags = quoteTagService.selectAllTag();

                model.addAttribute("tags", tags);
                model.addAttribute("user", user);
                model.addAttribute("book", book);
                return "createQuote";
            } catch (IllegalArgumentException e) {
                // 사용자 정보를 찾지 못했을 때
                return "oauthLogin";
            }
        }
        // 로그인되어 있지 않을 때
        else {
            return "oauthLogin";
        }
    }

    @GetMapping("/books/{bookId}/quotes/{quoteId}")
    public String lookQuote(@PathVariable("bookId") Long bookId,
                            @PathVariable("quoteId") Long quoteId,
                            @CookieValue(name = "refresh_token", required = false) String refreshToken,
                            Model model) {
        boolean loggedIn = false; // 기본적으로 로그아웃 상태로 설정

        if (refreshToken != null) {
            try {
                RefreshToken refreshTokenEntity = refreshTokenService.findByRefreshToken(refreshToken);
                if (refreshTokenEntity != null) {
                    Long refreshTokenId = refreshTokenEntity.getId();
                    userService.findByRefreshTokenId(refreshTokenId);
                    // 사용자 정보가 정상적으로 조회되었을 때
                    loggedIn = true; // 로그인 상태로 변경
                } else {
                    // refreshToken이 존재하지 않는 경우
                    loggedIn = false; // 로그아웃 상태로 유지
                    return "oauthLogin";
                }
            } catch (IllegalArgumentException e) {
                // 사용자 정보를 찾지 못했을 때
                loggedIn = false; // 로그아웃 상태로 유지
                return "oauthLogin";
            }
        }else{
            return "oauthLogin";
        }

        // 로그인 상태를 모델에 추가
        model.addAttribute("loggedIn", loggedIn);

        // 책과 인용구 정보를 모델에 추가
        Book book = bookService.selectBook(bookId);
        Quote quote = quoteService.selectQuote(quoteId);
        Long refreshTokenId = refreshTokenService.findByRefreshToken(refreshToken).getId();
        User user = userService.findByRefreshTokenId(refreshTokenId);
        List<Comment> comments = quote.getComments();
        List<QuoteTag> tags = quote.getQuoteTags();

        model.addAttribute("user", user);
        model.addAttribute("book", book);
        model.addAttribute("quote", quote);
        model.addAttribute("comments", comments);
        model.addAttribute("tags", tags);

        return "quote";
    }

    //전체 책갈피 보기
    @GetMapping("/quotes")
    public String quotes(@CookieValue(name = "refresh_token", required = false) String refreshToken, Model model) {
        boolean loggedIn = false; // 기본적으로 로그아웃 상태로 설정

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

        // 로그인 상태를 모델에 추가
        model.addAttribute("loggedIn", loggedIn);

        // 인용구 목록을 모델에 추가
        List<Quote> quotes = quoteService.findAllQuotes();
        model.addAttribute("quotes", quotes);

        return "allQuoteList";
    }
    @GetMapping("/books/{bookId}/quotes/{quoteId}/update")
    public String updateQuote(@PathVariable("bookId") Long bookId,
                              @PathVariable("quoteId") Long quoteId,
                              @CookieValue(name = "refresh_token", required = false) String refreshToken,
                              Model model){
        boolean loggedIn = false; // 기본적으로 로그아웃 상태로 설정

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

        // 로그인 상태를 모델에 추가
        model.addAttribute("loggedIn", loggedIn);

        // 로그인되어 있을 때
        if(loggedIn){
            try {
                Long refreshTokenId = refreshTokenService.findByRefreshToken(refreshToken).getId();
                User user = userService.findByRefreshTokenId(refreshTokenId);
                Book book = bookService.selectBook(bookId);
                Quote quote = quoteService.selectQuote(quoteId);
                model.addAttribute("user", user);
                model.addAttribute("book", book);
                model.addAttribute("quote", quote);
                return "updateQuote";
            } catch (IllegalArgumentException e) {
                // 사용자 정보를 찾지 못했을 때
                return "oauthLogin";
            }
        }
        // 로그인되어 있지 않을 때
        else {
            return "oauthLogin";
        }
    }
    @GetMapping("/books/{bookId}/quotes/{quoteId}/delete")
    public String deleteQuote(@PathVariable("bookId") Long bookId,
                              @PathVariable("quoteId") Long quoteId,
                              @CookieValue(name = "refresh_token", required = false) String refreshToken,
                              Model model){
        boolean loggedIn = false; // 기본적으로 로그아웃 상태로 설정

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

        // 로그인 상태를 모델에 추가
        model.addAttribute("loggedIn", loggedIn);

        // 로그인되어 있을 때
        if(loggedIn){
            try {
                Long refreshTokenId = refreshTokenService.findByRefreshToken(refreshToken).getId();
                Book book = bookService.selectBook(bookId);
                Quote quote = quoteService.selectQuote(quoteId);
                model.addAttribute("book", book);
                model.addAttribute("quote", quote);
                return "deleteQuote";
            } catch (IllegalArgumentException e) {
                // 사용자 정보를 찾지 못했을 때
                return "oauthLogin";
            }
        }
        // 로그인되어 있지 않을 때
        else {
            return "oauthLogin";
        }
    }


}
