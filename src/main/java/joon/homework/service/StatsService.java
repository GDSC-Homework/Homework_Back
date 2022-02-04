package joon.homework.service;

import joon.homework.dto.stats.response.GetMonthAllLatestStatsResDto;
import joon.homework.repository.StatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final StatsRepository statsRepository;
    private final AuthService authService;

    public GetMonthAllLatestStatsResDto getMonthAllLatestStats(String token) {
        authService.verifyToken(token);

        // 리턴타입 형식 정하고 통계 어떤식으로 보여줄지 정하기
    }
}
