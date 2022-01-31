package joon.homework.dto.google;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserInfoDto {

    private String iss;
    private String sub;
    private String azp;
    private String aud;
    private String iat;
    private String exp;
    private String email;
    private String email_verified;
    private String name;
    private String picture;
    private String given_name;
    private String family_name;
    private String locale;
}
