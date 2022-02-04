package joon.homework.service;

import joon.homework.entity.*;
import joon.homework.repository.*;
import joon.homework.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouletteService {

    private final JwtUtil jwtUtil;
    private final RouletteRepository rouletteRepository;
    private final HouseworkRepository houseworkRepository;
    private final UserRepository userRepository;
    private final ParticipateRepository participateRepository;
    private final BankRepository bankRepository;
    private final AuthService authService;

    public void rouletteResult(String token, Long houseworkId, Long userId) {
        authService.verifyToken(token);

        Optional<Housework> housework = houseworkRepository.findById(houseworkId);
        housework.get().setUserId(userId);

        houseworkRepository.save(housework.get());

        Long roomId = housework.get().getRoomId();

        Roulette roulette = rouletteRepository.findByRoomId(roomId);

        int price = roulette.getPrice();

        Bank bank = bankRepository.findByRoomId(roomId);
        bank.setDeposit(bank.getDeposit() + price);

        bankRepository.save(bank);
    }

    public int roulettePrice(String token) {
        authService.verifyToken(token);

        Long userId = jwtUtil.getId(token);
        Optional<User> user = userRepository.findById(userId);

        Participate participate = participateRepository.findByUserId(user.get().getId());

        Long roomId = participate.getRoomId();

        Roulette roulette = rouletteRepository.findByRoomId(roomId);

        return roulette.getPrice();
    }
}
