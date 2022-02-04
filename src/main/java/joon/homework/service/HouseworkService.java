package joon.homework.service;

import joon.homework.dto.housework.response.GetAllHouseworkResDto;
import joon.homework.entity.Housework;
import joon.homework.entity.Participate;
import joon.homework.entity.Stats;
import joon.homework.entity.User;
import joon.homework.repository.HouseworkRepository;
import joon.homework.repository.ParticipateRepository;
import joon.homework.repository.StatsRepository;
import joon.homework.repository.UserRepository;
import joon.homework.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HouseworkService {

    private final JwtUtil jwtUtil;
    private final ParticipateRepository participateRepository;
    private final UserRepository userRepository;
    private final HouseworkRepository houseworkRepository;
    private final StatsRepository statsRepository;
    private final AuthService authService;

    public void createHousework(
            String token,
            String name,
            List<Long> userId,
            List<String> day,
            String startTime,
            String finishTime,
            Boolean repeat,
            int penalty,
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
                        .penalty(penalty)
                        .memo(memo)
                        .finished(false)
                        .build();

                houseworkRepository.save(housework);
            }
        }
    }

    public List<Housework> getMyHousework(String token) {
        authService.verifyToken(token);

        Long userId = jwtUtil.getId(token);
        Optional<User> user = userRepository.findById(userId);

        List<Housework> houseworkList = houseworkRepository.findAllByUserId(user.get().getId());

        return houseworkList;
    }

    public GetAllHouseworkResDto getAllHousework(String token) {
        authService.verifyToken(token);

        Long userId = jwtUtil.getId(token);
        Optional<User> user = userRepository.findById(userId);

        Participate participate = participateRepository.findByUserId(user.get().getId());
        Long roomId = participate.getRoomId();

        List<Housework> allHousework = houseworkRepository.findAllByRoomId(roomId);
        List<Housework> myHousework = houseworkRepository.findAllByUserId(user.get().getId());

        for(int i=0; i<myHousework.size(); i++) {
            allHousework.remove(myHousework.get(i));
        }

        GetAllHouseworkResDto getAllHouseworkResDto = GetAllHouseworkResDto.builder()
                .myHousework(myHousework)
                .otherHousework(allHousework)
                .build();

        return getAllHouseworkResDto;
    }

    public void doneHousework(String token, Long houseworkId) {
        authService.verifyToken(token);

        Optional<Housework> housework = houseworkRepository.findById(houseworkId);
        housework.get().setFinished(true);

        houseworkRepository.save(housework.get());

        String[] workList = {"설거지", "분리수거", "청소", "욕실 청소", "세탁", "장보기", "요리"};
        String work = housework.get().getName();

        Stats stats = Stats.builder()
                .roomId(housework.get().getRoomId())
                .userId(housework.get().getUserId())
                .build();

        for(int i=0; i<workList.length; i++) {
            if(work.equals(workList[i])) {
                stats.setCategory(work);
                break;
            }
            if(i == workList.length - 1) {
                stats.setCategory("기타");
            }
        }

        Stats statsAll = Stats.builder()
                .roomId(housework.get().getRoomId())
                .userId(housework.get().getUserId())
                .category("전체")
                .build();

        statsRepository.save(stats);
        statsRepository.save(statsAll);
    }
}
