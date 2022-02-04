package joon.homework.service;

import joon.homework.dto.stats.response.GetMonthLatestStatsResDto;
import joon.homework.dto.stats.response.GetMonthPassedResDto;
import joon.homework.dto.stats.response.GetWeekLatestStatsResDto;
import joon.homework.dto.stats.response.NameAndCount;
import joon.homework.entity.MonthStats;
import joon.homework.entity.Participate;
import joon.homework.entity.User;
import joon.homework.repository.MonthStatsRepository;
import joon.homework.repository.ParticipateRepository;
import joon.homework.repository.StatsRepository;
import joon.homework.repository.UserRepository;
import joon.homework.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final JwtUtil jwtUtil;
    private final StatsRepository statsRepository;
    private final UserRepository userRepository;
    private final ParticipateRepository participateRepository;
    private final MonthStatsRepository monthStatsRepository;
    private final AuthService authService;

    public GetMonthLatestStatsResDto getMonthLatestStats(String token, String category) {
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

        int max = 0;

        for(int i=0; i<participantsId.size(); i++) {
            Optional<User> currentUser = userRepository.findById(participantsId.get(i));
            String name = currentUser.get().getName();
            int count = statsRepository.countByUserIdAndCategoryAndCreatedAtBetween(participantsId.get(i), category, start, end);
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

        GetMonthLatestStatsResDto result = GetMonthLatestStatsResDto.builder()
                .winners(winners)
                .result(nameAndCountList)
                .build();

        return result;
    }

    public GetWeekLatestStatsResDto getWeekLatestStats(String token, String category) {
        authService.verifyToken(token);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String startWeek = formatter.format(calendar.getTime());
        LocalDate startLD = LocalDate.parse(startWeek, DateTimeFormatter.ISO_DATE);

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        calendar.add(calendar.DATE, 7);
        String endWeek = formatter.format(calendar.getTime());
        LocalDate endLD = LocalDate.parse(endWeek, DateTimeFormatter.ISO_DATE);

        LocalDateTime start = startLD.atTime(0, 0, 0);
        LocalDateTime end = endLD.atTime(23, 59, 59);

        Long userId = jwtUtil.getId(token);
        Optional<User> user = userRepository.findById(userId);

        Participate participate = participateRepository.findByUserId(user.get().getId());

        Long roomId = participate.getRoomId();

        List<Participate> participateList = participateRepository.findAllByRoomId(roomId);

        List<Long> participantsId = new ArrayList<>();

        for(int i=0; i<participateList.size(); i++) {
            participantsId.add(i, participateList.get(i).getUserId());
        }

        List<List<NameAndCount>> counts = new ArrayList<>();
        int[] totals = new int[participantsId.size()];
        for(int j=0; j<participantsId.size(); j++) {
            totals[j] = 0;
        }

        for(int i=0; i<7; i++) {
            LocalDateTime from = start;
            LocalDateTime to = start.plusHours(23);
            to = to.plusMinutes(59);
            to = to.plusSeconds(59);

            List<NameAndCount> nameAndCountList = new ArrayList<>();

            for(int j=0; j<participantsId.size(); j++) {
                Optional<User> currentUser = userRepository.findById(participantsId.get(j));
                String name = currentUser.get().getName();
                int count = statsRepository.countByUserIdAndCategoryAndCreatedAtBetween(participantsId.get(j), category, from, to);
                totals[j] += count;
                NameAndCount nameAndCount = NameAndCount.builder()
                        .userId(participantsId.get(j))
                        .name(name)
                        .count(count)
                        .build();
                nameAndCountList.add(j, nameAndCount);
            }

            counts.add(i, nameAndCountList);

            start = start.plusDays(1);
        }

        int max = 0;
        for(int i=0; i<totals.length; i++) {
            if(totals[i] > max) {
                max = totals[i];
            }
        }

        List<String> winners = new ArrayList<>();

        int j = 0;
        for(int i=0; i<totals.length; i++) {
            if(totals[i] == max) {
                Optional<User> currentUser = userRepository.findById(participantsId.get(i));
                String name = currentUser.get().getName();
                winners.add(j, name);
                j++;
            }
        }

        GetWeekLatestStatsResDto result = GetWeekLatestStatsResDto.builder()
                .winners(winners)
                .result(counts)
                .build();

        return result;
    }

    public int getMonthPassedMax(String token) {
        authService.verifyToken(token);

        Long userId = jwtUtil.getId(token);
        Optional<User> user = userRepository.findById(userId);

        Participate participate = participateRepository.findByUserId(user.get().getId());

        Long roomId = participate.getRoomId();

        List<MonthStats> monthStatsList = monthStatsRepository.findAllByRoomId(roomId);

        int max = 0;
        for(int i=0; i<monthStatsList.size(); i++) {
            if(max < monthStatsList.get(i).getPassed()) {
                max = monthStatsList.get(i).getPassed();
            }
        }

        return max;
    }

    public GetMonthPassedResDto getMonthPassed(String token, int passed) {
        authService.verifyToken(token);

        Long userId = jwtUtil.getId(token);
        Optional<User> user = userRepository.findById(userId);

        Participate participate = participateRepository.findByUserId(user.get().getId());

        Long roomId = participate.getRoomId();

        List<MonthStats> monthStatsList = monthStatsRepository.findAllByRoomIdAndPassed(roomId, passed);

        List<String> winners = new ArrayList<>();
        List<NameAndCount> nameAndCountList = new ArrayList<>();

        for(int i=0; i<monthStatsList.size(); i++) {
            MonthStats monthStats = monthStatsList.get(i);
            NameAndCount nameAndCount = NameAndCount.builder()
                    .userId(monthStats.getUserId())
                    .name(monthStats.getUserName())
                    .count(monthStats.getCount())
                    .build();
            nameAndCountList.add(i, nameAndCount);
        }

        winners.add(0, monthStatsList.get(0).getWinner());

        GetMonthPassedResDto getMonthPassedResDto = GetMonthPassedResDto.builder()
                .winners(winners)
                .result(nameAndCountList)
                .build();

        return getMonthPassedResDto;
    }
}
