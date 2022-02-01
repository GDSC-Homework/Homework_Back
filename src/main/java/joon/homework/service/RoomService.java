package joon.homework.service;

import joon.homework.dto.room.response.CreateRoomResDto;
import joon.homework.entity.Participate;
import joon.homework.entity.Room;
import joon.homework.entity.User;
import joon.homework.repository.ParticipateRepository;
import joon.homework.repository.RoomRepository;
import joon.homework.repository.UserRepository;
import joon.homework.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ParticipateRepository participateRepository;
    private final AuthService authService;

    public CreateRoomResDto createRoom(String token) {
        authService.verifyToken(token);

        Long userId = jwtUtil.getId(token);
        Optional<User> user = userRepository.findById(userId);

        String roomCode = createRoomCode();

        Room room = Room.builder()
                .code(roomCode)
                .build();

        roomRepository.save(room);

        Room savedRoom = roomRepository.findByCode(roomCode);

        Participate participate = Participate.builder()
                .roomId(savedRoom.getId())
                .userId(user.get().getId())
                .build();

        participateRepository.save(participate);

        CreateRoomResDto createRoomResDto = CreateRoomResDto.builder()
                .roomCode(roomCode)
                .roomId(savedRoom.getId())
                .build();

        return createRoomResDto;
    }

    private String createRoomCode() {
        String result;
        do {
            char[] tmp = new char[6];
            for(int i=0; i<tmp.length; i++) {
                int div = (int) Math.floor( Math.random() * 2 );
                if(div == 0) { // 0이면 숫자로
                    tmp[i] = (char) (Math.random() * 10 + '0') ;
                } else { //1이면 알파벳
                    tmp[i] = (char) (Math.random() * 26 + 'A') ;
                }
            }
            result = new String(tmp);
        } while(checkRoomCode(result));

        return result;
    }

    private Boolean checkRoomCode(String roomCode) {
        List<Room> allRooms = roomRepository.findAll();
        for (int i = 0; i < allRooms.size(); i++) {
            if(roomCode.equals(allRooms.get(i).getCode())) {
                return true;
            }
        }
        return false;
    }
}