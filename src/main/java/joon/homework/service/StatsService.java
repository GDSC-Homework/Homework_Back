package joon.homework.service;

import joon.homework.dto.stats.response.GetMonthAllLatestStatsResDto;
import joon.homework.dto.stats.response.NameAndCount;
import joon.homework.entity.Participate;
import joon.homework.entity.User;
import joon.homework.repository.ParticipateRepository;
import joon.homework.repository.StatsRepository;
import joon.homework.repository.UserRepository;
import joon.homework.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final JwtUtil jwtUtil;
    private final StatsRepository statsRepository;
    private final UserRepository userRepository;
    private final ParticipateRepository participateRepository;
    private final AuthService authService;

    public GetMonthAllLatestStatsResDto getMonthLatestStats(String token, String category) {
        authService.verifyToken(token);

        LocalDate startLD = YearMonth.now().atDay(1);
        LocalDate endLD   = YearMonth.now().atEndOfMonth();

        LocalDateTime start = startLD.atTime(0, 0, 0);
        LocalDateTime end = endLD.atTime(23, 59, 59);

        System.out.println(start);
        System.out.println(end);

        Long userId = jwtUtil.getId(token);
        Optional<User> user = userRepository.findById(userId);

        Participate participate = participateRepository.findByUserId(user.get().getId());

        Long roomId = participate.getRoomId();

        List<Participate> participateList = participateRepository.findAllByRoomId(roomId);

        List<Long> participantsId = new ArrayList<>();

        for(int i=0; i<participateList.size(); i++) {
            participantsId.add(i, participateList.get(i).getUserId());
        }

        List<NameAndCount> nameAndCountList = new ArrayList<>();

        Long max = 0L;

        for(int i=0; i<participantsId.size(); i++) {
            Optional<User> currentUser = userRepository.findById(participantsId.get(i));
            String name = currentUser.get().getName();
            Long count = statsRepository.countByUserIdAndCategoryAndCreatedAtBetween(participantsId.get(i), category, start, end);
            if(count > max) {
                max = count;
            }
            NameAndCount nameAndCount = NameAndCount.builder()
                    .userId(participantsId.get(i))
                    .name(name)
                    .count(count)
                    .build();
            nameAndCountList.add(i, nameAndCount);
        }

        List<String> winners = new ArrayList<>();

        int winnerCnt = 0;
        for(int i=0; i<nameAndCountList.size(); i++) {
            if(nameAndCountList.get(i).getCount() == max) {
                winners.add(winnerCnt, nameAndCountList.get(i).getName());
                winnerCnt++;
            }
        }

        GetMonthAllLatestStatsResDto result = GetMonthAllLatestStatsResDto.builder()
                .winners(winners)
                .result(nameAndCountList)
                .build();

        return result;
    }
}
