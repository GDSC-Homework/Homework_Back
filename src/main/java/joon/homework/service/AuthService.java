package joon.homework.service;

import joon.homework.dto.google.UserInfoDto;
import joon.homework.entity.User;
import joon.homework.enums.Role;
import joon.homework.exception.NotLoggedInException;
import joon.homework.repository.UserRepository;
import joon.homework.util.JwtUtil;
import joon.homework.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;
    private final UserRepository userRepository;
    private final GoogleService googleService;

    public String googleLogin(String idToken) {
        String jwt;
        UserInfoDto userInfoDto = googleService.getUserInfoByIdToken(idToken);

        User user = userRepository.findByEmail(userInfoDto.getEmail());

//        User user = userRepository.findByEmail("orijoon98@gmail.com");

        if(user == null) {
            user = User.builder()
                    .name(userInfoDto.getName())
                    .email(userInfoDto.getEmail())
                    .picture(userInfoDto.getPicture())
                    .role(Role.ROLE_USER)
                    .build();
//            user = User.builder()
//                    .name("공혁준")
//                    .email("orijoon98@gmail.com")
//                    .picture("")
//                    .role(Role.ROLE_USER)
//                    .build();

            userRepository.save(user);
        }

        jwt = jwtUtil.generateToken(user);
        redisUtil.setDataExpire(user.getEmail(), jwt, JwtUtil.TOKEN_VALIDATION_SECOND);

        return jwt;
    }

    public Long checkLoggedIn(String token) {
        verifyToken(token);

        String email = jwtUtil.getEmail(token);
        User user = userRepository.findByEmail(email);

        return user.getId();
    }

    public void verifyToken(String token) throws NotLoggedInException{
        String email = jwtUtil.getEmail(token);
        User user = userRepository.findByEmail(email);
        Boolean result = jwtUtil.validateToken(token, user);

        if(!result) {
            throw new NotLoggedInException();
        }
    }
}
