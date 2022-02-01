package joon.homework.controller;

import joon.homework.dto.ResponseDto;
import joon.homework.dto.room.request.CheckRoomReqDto;
import joon.homework.dto.room.request.CreateRoomReqDto;
import joon.homework.dto.room.request.ParticipateRoomReqDto;
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
}
