package joon.homework.controller;

import joon.homework.dto.ResponseDto;
import joon.homework.dto.housework.request.CreateHouseReqDto;
import joon.homework.service.HouseworkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/housework")
@Slf4j
public class HouseworkController {

    private final HouseworkService houseworkService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createHousework(@RequestBody CreateHouseReqDto createHouseReqDto) {
        houseworkService.createHousework(
                createHouseReqDto.getToken(),
                createHouseReqDto.getName(),
                createHouseReqDto.getUserId(),
                createHouseReqDto.getDay(),
                createHouseReqDto.getStartTime(),
                createHouseReqDto.getFinishTime(),
                createHouseReqDto.getRepeat(),
                createHouseReqDto.getMemo()
        );

        log.info("/api/housework/create");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("집안일 추가 성공")
                        .data(null)
                        .build()
        );
    }
}
