package joon.homework.controller;

import joon.homework.dto.ResponseDto;
import joon.homework.dto.bank.request.AddDepositReqDto;
import joon.homework.dto.bank.request.GetDepositReqDto;
import joon.homework.dto.bank.request.MinusDepositReqDto;
import joon.homework.service.BankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bank")
@Slf4j
public class BankController {

    private final BankService bankService;

    @PostMapping("/deposit")
    public ResponseEntity<ResponseDto> getDeposit(@RequestBody GetDepositReqDto getDepositReqDto) {
        int deposit = bankService.getDeposit(getDepositReqDto.getToken());

        log.info("/api/bank/deposit");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("잔액 조회 성공")
                        .data(deposit)
                        .build()
        );
    }

    @PostMapping("/deposit/add")
    public ResponseEntity<ResponseDto> addDeposit(@RequestBody AddDepositReqDto addDepositReqDto) {
        int deposit = bankService.addDeposit(addDepositReqDto.getToken(), addDepositReqDto.getAmount());

        log.info("/api/bank/deposit/add");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("충전 성공")
                        .data(deposit)
                        .build()
        );
    }

    @PostMapping("/deposit/minus")
    public ResponseEntity<ResponseDto> minusDeposit(@RequestBody MinusDepositReqDto minusDepositReqDto) {
        int deposit = bankService.minusDeposit(minusDepositReqDto.getToken(), minusDepositReqDto.getAmount());

        log.info("/api/bank/deposit/minus");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("이체 성공")
                        .data(deposit)
                        .build()
        );
    }
}
