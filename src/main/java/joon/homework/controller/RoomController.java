package joon.homework.controller;

import joon.homework.dto.ResponseDto;
import joon.homework.dto.room.request.CreateRoomReqDto;
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

        String roomCode = roomService.createRoom(createRoomReqDto.getToken());

        log.info("/api/room/create");

        return ResponseEntity.status(200).body(
                ResponseDto.builder()
                        .status(200)
                        .message("로그인 성공")
                        .data(roomCode)
                        .build()
        );
    }
}
