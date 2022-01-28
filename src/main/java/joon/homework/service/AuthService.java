package joon.homework.service;

import joon.homework.entity.User;
import joon.homework.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public User googleLogin(String idToken) {
        System.out.println(idToken);

        User user = User.builder().build();

        return user;
    }
}
