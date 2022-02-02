package joon.homework.controller;

import joon.homework.dto.ResponseDto;
import joon.homework.dto.auth.request.CheckLoggedInReqDto;
import joon.homework.dto.auth.request.GetProfileReqDto;
import joon.homework.dto.auth.request.GoogleLoginReqDto;
import joon.homework.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/google/login")
    public ResponseEntity<ResponseDto> googleLogin(@RequestBody GoogleLoginReqDto googleLoginReqDto) {

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

    @PostMapping("/check")
    public ResponseEntity<ResponseDto> checkLoggedIn(@RequestBody CheckLoggedInReqDto checkLoggedInReqDto) {

        Long id = authService.checkLoggedIn(checkLoggedInReqDto.getToken());

        log.info("/api/auth/check");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("로그인 유지")
                        .data(id)
                        .build()
        );
    }

    @PostMapping("/profile")
    public ResponseEntity<ResponseDto> getProfile(@RequestBody GetProfileReqDto getProfileReqDto) {

        String name = authService.getProfile(getProfileReqDto.getToken(), getProfileReqDto.getUserId());

        log.info("/api/auth/profile");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("유저 프로필 요청 성공")
                        .data(name)
                        .build()
        );
    }
}
