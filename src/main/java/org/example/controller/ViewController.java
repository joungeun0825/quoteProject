package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.Quote;
import org.example.domain.Tag;
import org.example.domain.User;
import org.example.service.QuoteTagService;
import org.example.service.RefreshTokenService;
import org.example.service.TagService;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Controller
public class ViewController {
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;
    private final TagService tagService;
    private final QuoteTagService quoteTagService;

    @GetMapping("/")
    public String home(@CookieValue(name = "refresh_token", required = false) String refreshToken, Model model){

        List<Tag> tagList = tagService.selectAllTags();
        // 랜덤 객체 생성
        Random random = new Random();
        // 리스트의 크기에서 랜덤 인덱스 생성
        int randomIndex = random.nextInt(tagList.size());
        // 랜덤으로 선택된 요소 출력
        Tag randomTag = tagList.get(randomIndex);

        List<Quote> quotes = quoteTagService.selectQuotes(randomTag);


        if(refreshTokenService.findByRefreshToken(refreshToken) == null){
            model.addAttribute("loggedIn", false);
            model.addAttribute("quotes", quotes);
            model.addAttribute("randomTag", randomTag);
            return "home";
        }else {
            try {
                Long refreshTokenId = refreshTokenService.findByRefreshToken(refreshToken).getId();
                userService.findByRefreshTokenId(refreshTokenId);
                // 사용자 정보가 정상적으로 조회되었을 때
                model.addAttribute("loggedIn", true);
                model.addAttribute("quotes", quotes);
                model.addAttribute("randomTag", randomTag);
                return "home";
            } catch (IllegalArgumentException e) {
                // 사용자 정보를 찾지 못했을 때
                model.addAttribute("loggedIn", false);
                model.addAttribute("quotes", quotes);
                model.addAttribute("randomTag", randomTag);
                return "home";
            }
        }
    }
    @GetMapping("/books/create")
    public String createBookView(@CookieValue(name = "refresh_token", required = false) String refreshToken, Model model){
        if(refreshTokenService.findByRefreshToken(refreshToken) == null){
            model.addAttribute("loggedIn", false);
            return "createBook";
        }else {
            try {
                Long refreshTokenId = refreshTokenService.findByRefreshToken(refreshToken).getId();
                userService.findByRefreshTokenId(refreshTokenId);
                // 사용자 정보가 정상적으로 조회되었을 때
                model.addAttribute("loggedIn", true);
                return "createBook";
            } catch (IllegalArgumentException e) {
                // 사용자 정보를 찾지 못했을 때
                model.addAttribute("loggedIn", false);
                return "createBook";
            }
        }
    }



}
