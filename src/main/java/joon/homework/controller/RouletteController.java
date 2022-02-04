package joon.homework.controller;

import joon.homework.dto.ResponseDto;
import joon.homework.dto.roulette.request.RoulettePriceReqDto;
import joon.homework.dto.roulette.request.RouletteResultReqDto;
import joon.homework.service.RouletteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roulette")
@Slf4j
public class RouletteController {

    private final RouletteService rouletteService;

    @PostMapping("/result")
    public ResponseEntity<ResponseDto> rouletteResult(@RequestBody RouletteResultReqDto rouletteResultReqDto) {
        rouletteService.rouletteResult(rouletteResultReqDto.getToken(), rouletteResultReqDto.getHouseworkId(), rouletteResultReqDto.getUserId());

        log.info("/api/roulette/result");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("룰렛 결과 적용 완료")
                        .data(null)
                        .build()
        );
    }

    @PostMapping("/price")
    public ResponseEntity<ResponseDto> roulettePrice(@RequestBody RoulettePriceReqDto roulettePriceReqDto) {
        int price = rouletteService.roulettePrice(roulettePriceReqDto.getToken());

        log.info("/api/roulette/price");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("룰렛 이용료 조회 완료")
                        .data(price)
                        .build()
        );
    }
}
