package joon.homework.controller;

import joon.homework.dto.ResponseDto;
import joon.homework.dto.auth.request.GoogleLoginReqDto;
import joon.homework.entity.User;
import joon.homework.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/google/login")
    public ResponseEntity<ResponseDto> googleLogin(@RequestBody GoogleLoginReqDto googleLoginReqDto, HttpServletResponse res) {

        String token = authService.googleLogin(googleLoginReqDto.getIdToken());

        log.info("/api/auth/google/login");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("로그인 성공")
                        .data(token)
                        .build()
        );
    }
}
