package joon.homework.service;

import joon.homework.entity.Housework;
import joon.homework.entity.Participate;
import joon.homework.repository.HouseworkRepository;
import joon.homework.repository.ParticipateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HouseworkService {

    private final ParticipateRepository participateRepository;
    private final HouseworkRepository houseworkRepository;
    private final AuthService authService;

    public void createHousework(
            String token,
            String name,
            List<Long> userId,
            List<String> day,
            String startTime,
            String finishTime,
            Boolean repeat,
            String memo
    ) {
        authService.verifyToken(token);

        Participate participate = participateRepository.findByUserId(userId.get(0));
        Long roomId = participate.getRoomId();

        for(int i=0; i<userId.size(); i++) {
            for(int j=0; j<day.size(); j++) {
                Housework housework = Housework.builder()
                        .name(name)
                        .userId(userId.get(i))
                        .roomId(roomId)
                        .day(day.get(j))
                        .startTime(startTime)
                        .finishTime(finishTime)
                        .repeat(repeat)
                        .memo(memo)
                        .build();

                houseworkRepository.save(housework);
            }
        }
    }
}
