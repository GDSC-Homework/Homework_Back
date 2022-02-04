package joon.homework.controller;

import joon.homework.dto.ResponseDto;
import joon.homework.dto.room.request.*;
import joon.homework.dto.room.response.CheckRoomResDto;
import joon.homework.dto.room.response.CreateRoomResDto;
import joon.homework.service.RoomService;
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
@RequestMapping("/api/room")
@Slf4j
public class RoomController {

    private final RoomService roomService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createRoom(@RequestBody CreateRoomReqDto createRoomReqDto) {

        CreateRoomResDto createRoomResDto = roomService.createRoom(createRoomReqDto.getToken());

        log.info("/api/room/create");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("방 생성 성공")
                        .data(createRoomResDto)
                        .build()
        );
    }

    @PostMapping("/participate")
    public ResponseEntity<ResponseDto> participateRoom(@RequestBody ParticipateRoomReqDto participateRoomReqDto) {

        Long roomId = roomService.participateRoom(participateRoomReqDto.getToken(), participateRoomReqDto.getRoomCode());

        log.info("/api/room/participate");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("방 참가 성공")
                        .data(roomId)
                        .build()
        );
    }

    @PostMapping("/check")
    public ResponseEntity<ResponseDto> checkRoom(@RequestBody CheckRoomReqDto checkRoomReqDto) {

        CheckRoomResDto checkRoomResDto = roomService.checkRoom(checkRoomReqDto.getToken());

        log.info("/api/room/check");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("방 참가 여부")
                        .data(checkRoomResDto)
                        .build()
        );
    }

    @PostMapping("/participants")
    public ResponseEntity<ResponseDto> getParticipants(@RequestBody GetParticipantsReqDto getParticipantsReqDto) {

        List<Long> participants = roomService.getParticipants(getParticipantsReqDto.getToken(), getParticipantsReqDto.getRoomId());

        log.info("/api/room/participants");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("방 참가 인원")
                        .data(participants)
                        .build()
        );
    }

    @PostMapping("/code")
    public ResponseEntity<ResponseDto> getCode(@RequestBody GetCodeReqDto getCodeReqDto) {
        String code = roomService.getCode(getCodeReqDto.getToken());

        log.info("/api/room/code");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("방 초대 코드")
                        .data(code)
                        .build()
        );
    }
}
