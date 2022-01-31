package joon.homework.service;

import joon.homework.dto.google.UserInfoDto;
import joon.homework.entity.User;
import joon.homework.enums.Role;
import joon.homework.repository.UserRepository;
import joon.homework.util.CookieUtil;
import joon.homework.util.JwtUtil;
import joon.homework.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;
    private final RedisUtil redisUtil;
    private final UserRepository userRepository;
    private final GoogleService googleService;

    public User googleLogin(String idToken) {
        UserInfoDto userInfoDto = googleService.getUserInfoByIdToken(idToken);

        Optional<User> user = userRepository.findByEmail(userInfoDto.getEmail());

        if(user.isEmpty()) {
            User newUser = User.builder()
                    .name(userInfoDto.getName())
                    .email(userInfoDto.getEmail())
                    .picture(userInfoDto.getPicture())
                    .role(Role.ROLE_USER)
                    .build();

            userRepository.save(newUser);

            return newUser;
        }

        return user.get();
    }

    public Map<String, Cookie> sendCookie(User user) {
        String jwt = jwtUtil.generateToken(user);
        String refreshJwt = jwtUtil.generateRefreshToken(user);
        Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, jwt);
        Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);
        redisUtil.setDataExpire(refreshJwt, user.getEmail(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);

        Map<String, Cookie> map = new HashMap<String, Cookie>();
        map.put(JwtUtil.ACCESS_TOKEN_NAME, accessToken);
        map.put(JwtUtil.REFRESH_TOKEN_NAME, refreshToken);

        return map;
    }
}
