package joon.homework.controller;

import joon.homework.dto.ResponseDto;
import joon.homework.dto.housework.request.CreateHouseReqDto;
import joon.homework.dto.housework.request.DoneHouseworkReqDto;
import joon.homework.dto.housework.request.GetAllHouseworkReqDto;
import joon.homework.dto.housework.request.GetMyHouseworkReqDto;
import joon.homework.dto.housework.response.GetAllHouseworkResDto;
import joon.homework.entity.Housework;
import joon.homework.service.HouseworkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
                createHouseReqDto.getPenalty(),
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

    @PostMapping("/my")
    public ResponseEntity<ResponseDto> getMyHousework(@RequestBody GetMyHouseworkReqDto getMyHouseworkReqDto) {
        List<Housework> myHousework = houseworkService.getMyHousework(getMyHouseworkReqDto.getToken());

        log.info("/api/housework/my");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("내 집안일 목록")
                        .data(myHousework)
                        .build()
        );
    }

    @PostMapping("/all")
    public ResponseEntity<ResponseDto> getAllHousework(@RequestBody GetAllHouseworkReqDto getAllHouseworkReqDto) {
        GetAllHouseworkResDto getAllHouseworkResDto = houseworkService.getAllHousework(getAllHouseworkReqDto.getToken());

        log.info("/api/housework/all");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("전체 집안일 목록")
                        .data(getAllHouseworkResDto)
                        .build()
        );
    }

    @PostMapping("/done")
    public ResponseEntity<ResponseDto> doneHousework(@RequestBody DoneHouseworkReqDto doneHouseworkReqDto) {
        houseworkService.doneHousework(doneHouseworkReqDto.getToken(), doneHouseworkReqDto.getHouseworkId());

        log.info("/api/housework/done");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("집안일 완료 처리 성공")
                        .data(null)
                        .build()
        );
    }
}
