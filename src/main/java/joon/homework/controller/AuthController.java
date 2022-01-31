package joon.homework.controller;

import joon.homework.dto.ResponseDto;
import joon.homework.dto.auth.request.GoogleLoginReqDto;
import joon.homework.entity.User;
import joon.homework.service.AuthService;
import joon.homework.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/google/login")
    public ResponseEntity<ResponseDto> googleLogin(@RequestBody GoogleLoginReqDto googleLoginReqDto, HttpServletResponse res) {

        User user = authService.googleLogin(googleLoginReqDto.getIdToken());
        Map<String, Cookie> map = authService.sendCookie(user);

        res.addCookie(map.get(JwtUtil.ACCESS_TOKEN_NAME));
        res.addCookie(map.get(JwtUtil.REFRESH_TOKEN_NAME));

        log.info("/api/auth/google/login");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("로그인 성공")
                        .data(user)
                        .build()
        );
    }
}
