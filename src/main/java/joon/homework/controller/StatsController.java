package joon.homework.controller;

import joon.homework.dto.ResponseDto;
import joon.homework.dto.stats.request.GetMonthLatestStatsReqDto;
import joon.homework.dto.stats.request.GetMonthPassedMaxReqDto;
import joon.homework.dto.stats.request.GetMonthPassedReqDto;
import joon.homework.dto.stats.request.GetWeekLatestStatsReqDto;
import joon.homework.dto.stats.response.GetMonthLatestStatsResDto;
import joon.homework.dto.stats.response.GetMonthPassedResDto;
import joon.homework.dto.stats.response.GetWeekLatestStatsResDto;
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
    public ResponseEntity<ResponseDto> getMonthLatestStats(@RequestBody GetMonthLatestStatsReqDto getMonthLatestStatsReqDto) {
        GetMonthLatestStatsResDto stats = statsService.getMonthLatestStats(getMonthLatestStatsReqDto.getToken(), getMonthLatestStatsReqDto.getCategory());

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
    public ResponseEntity<ResponseDto> getWeekLatestStats(@RequestBody GetWeekLatestStatsReqDto getWeekLatestStatsReqDto) {
        GetWeekLatestStatsResDto result = statsService.getWeekLatestStats(getWeekLatestStatsReqDto.getToken(), getWeekLatestStatsReqDto.getCategory());

        log.info("/api/stats/week/latest");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("이번주 주간 통계")
                        .data(result)
                        .build()
        );
    }

    @PostMapping("/month/passed/max")
    public ResponseEntity<ResponseDto> getMonthPassedMax(@RequestBody GetMonthPassedMaxReqDto getMonthPassedMaxReqDto) {
        int max = statsService.getMonthPassedMax(getMonthPassedMaxReqDto.getToken());

        log.info("/api/stats/month/passed/max");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("지난 월간 통계 수")
                        .data(max)
                        .build()
        );
    }

    @PostMapping("/month/passed")
    public ResponseEntity<ResponseDto> getMonthPassed(@RequestBody GetMonthPassedReqDto getMonthPassedReqDto) {
        GetMonthPassedResDto result = statsService.getMonthPassed(getMonthPassedReqDto.getToken(), getMonthPassedReqDto.getPassed());

        log.info("/api/stats/month/passed");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("지난 월간 통계")
                        .data(result)
                        .build()
        );
    }
}
