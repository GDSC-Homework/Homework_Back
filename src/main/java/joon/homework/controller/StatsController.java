package joon.homework.controller;

import joon.homework.dto.ResponseDto;
import joon.homework.dto.stats.request.GetMonthAllLatestStatsReqDto;
import joon.homework.dto.stats.response.GetMonthAllLatestStatsResDto;
import joon.homework.service.StatsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stats")
@Slf4j
public class StatsController {

    private final StatsService statsService;

    @PostMapping("/month/latest")
    public ResponseEntity<ResponseDto> getMonthLatestStats(@RequestBody GetMonthAllLatestStatsReqDto getMonthAllLatestStatsReqDto) {
        GetMonthAllLatestStatsResDto stats = statsService.getMonthLatestStats(getMonthAllLatestStatsReqDto.getToken(), getMonthAllLatestStatsReqDto.getCategory());

        log.info("/api/stats/month/latest");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("이번달 월간 통계")
                        .data(stats)
                        .build()
        );
    }

    @PostMapping("/week/latest")
    public ResponseEntity<ResponseDto> getWeekLatestStats(@RequestBody GetMonthAllLatestStatsReqDto getMonthAllLatestStatsReqDto) {
        statsService.getWeekLatestStats(getMonthAllLatestStatsReqDto.getToken(), getMonthAllLatestStatsReqDto.getCategory());

        log.info("/api/stats/week/latest");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("이번주 주간 통계")
                        .data(null)
                        .build()
        );
    }
}
