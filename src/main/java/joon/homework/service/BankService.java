package joon.homework.service;

import joon.homework.entity.Bank;
import joon.homework.entity.Participate;
import joon.homework.entity.User;
import joon.homework.repository.BankRepository;
import joon.homework.repository.ParticipateRepository;
import joon.homework.repository.UserRepository;
import joon.homework.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final ParticipateRepository participateRepository;
    private final BankRepository bankRepository;
    private final AuthService authService;

    public int getDeposit(String token) {
        authService.verifyToken(token);

        Long userId = jwtUtil.getId(token);
        Optional<User> user = userRepository.findById(userId);

        Participate participate = participateRepository.findByUserId(user.get().getId());

        Long roomId = participate.getRoomId();

        Bank bank = bankRepository.findByRoomId(roomId);

        return bank.getDeposit();
    }

    public int addDeposit(String token, int amount) {
        authService.verifyToken(token);

        Long userId = jwtUtil.getId(token);
        Optional<User> user = userRepository.findById(userId);

        Participate participate = participateRepository.findByUserId(user.get().getId());

        Long roomId = participate.getRoomId();

        Bank bank = bankRepository.findByRoomId(roomId);
        bank.setDeposit(bank.getDeposit() + amount);

        bankRepository.save(bank);

        return bank.getDeposit();
    }

    public int minusDeposit(String token, int amount) {
        authService.verifyToken(token);

        Long userId = jwtUtil.getId(token);
        Optional<User> user = userRepository.findById(userId);

        Participate participate = participateRepository.findByUserId(user.get().getId());

        Long roomId = participate.getRoomId();

        Bank bank = bankRepository.findByRoomId(roomId);
        bank.setDeposit(bank.getDeposit() - amount);

        bankRepository.save(bank);

        return bank.getDeposit();
    }
}
