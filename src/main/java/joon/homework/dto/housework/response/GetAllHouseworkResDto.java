package joon.homework.dto.housework.response;

import joon.homework.entity.Housework;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetAllHouseworkResDto {

    List<Housework> myHousework;
    List<Housework> otherHousework;

    @Builder
    public GetAllHouseworkResDto(List<Housework> myHousework, List<Housework> otherHousework) {
        this.myHousework = myHousework;
        this.otherHousework = otherHousework;
    }
}
