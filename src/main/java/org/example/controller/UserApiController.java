package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.domain.User;
import org.example.dto.AddUserRequest;
import org.example.dto.UserResponse;
import org.example.dto.UserSignUpDto;
import org.example.dto.UserWithdrawDto;
import org.example.service.MyLoginUserService;
import org.example.service.RefreshTokenService;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService userService;
    private final MyLoginUserService myLoginUserService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/user")
    public String signup(AddUserRequest request) {
        userService.save(request);
        return "redirect:/login";
    }
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody UserSignUpDto userSignUpDto) {
        try {
            myLoginUserService.signUp(userSignUpDto);
            return ResponseEntity.ok("회원가입 성공");
        } catch (MethodArgumentNotValidException e) {
            // MethodArgumentNotValidException에서 오류 메시지를 추출하여 반환
            StringBuilder errorMessageBuilder = new StringBuilder();
            e.getBindingResult().getAllErrors().forEach(error -> {
                errorMessageBuilder.append(error.getDefaultMessage()).append("\n");
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessageBuilder.toString());
        } catch (Exception e) {
            // 기타 예외가 발생한 경우에도 400 Bad Request 상태 코드를 반환
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패: " + e.getMessage());
        }
    }
    /**
     * 회원탈퇴
     */
    @DeleteMapping("/member")
    @ResponseStatus(HttpStatus.OK)
    public void withdraw(@Valid @RequestBody UserWithdrawDto userWithdrawDto) throws Exception {
        myLoginUserService.withdraw(userWithdrawDto.checkPassword());
    }

    @GetMapping("/myPage")
    public String myPage(@CookieValue(name = "refresh_token", required = false) String refreshToken, Model model) {
        boolean loggedIn = false; // 기본적으로 로그아웃 상태로 설정

        if (refreshToken != null) {
            try {
                Long refreshTokenId = refreshTokenService.findByRefreshToken(refreshToken).getId();
                userService.findByRefreshTokenId(refreshTokenId);
                // 사용자 정보가 정상적으로 조회되었을 때
                loggedIn = true; // 로그인 상태로 변경
            } catch (IllegalArgumentException e) {
                // 사용자 정보를 찾지 못했을 때
                loggedIn = false; // 로그아웃 상태로 유지
            }
        }

        // 로그인 상태를 모델에 추가
        model.addAttribute("loggedIn", loggedIn);

        if (loggedIn) {
            // 로그인된 경우 사용자 정보를 가져와서 모델에 추가
            Long refreshTokenId = refreshTokenService.findByRefreshToken(refreshToken).getId();
            User user = userService.findByRefreshTokenId(refreshTokenId);
            model.addAttribute("user", user);
        }

        return loggedIn ? "myPage" : "oauthLogin";
    }


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

}