package joon.homework.service;

import joon.homework.dto.google.UserInfoDto;
import joon.homework.entity.User;
import joon.homework.exception.NotLoggedInException;
import joon.homework.repository.UserRepository;
import joon.homework.util.JwtUtil;
import joon.homework.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

        if(user == null) {
            user = User.builder()
                    .name(userInfoDto.getName())
                    .email(userInfoDto.getEmail())
                    .picture(userInfoDto.getPicture())
                    .build();

            userRepository.save(user);
        }

        jwt = jwtUtil.generateToken(user);
        redisUtil.setDataExpire(user.getEmail(), jwt, JwtUtil.TOKEN_VALIDATION_SECOND);

        return jwt;
    }

    public Long checkLoggedIn(String token) {
        verifyToken(token);

        Long id = jwtUtil.getId(token);
        Optional<User> user = userRepository.findById(id);

        return user.get().getId();
    }

    public void verifyToken(String token) throws NotLoggedInException{
        Long id = jwtUtil.getId(token);
        Optional<User> user = userRepository.findById(id);
        Boolean result = jwtUtil.validateToken(token, user.get());

        if(!result) {
            throw new NotLoggedInException();
        }
    }

    public String getProfile(String token, Long userId) {
        verifyToken(token);

        Optional<User> user = userRepository.findById(userId);

        return user.get().getName();
    }
}
