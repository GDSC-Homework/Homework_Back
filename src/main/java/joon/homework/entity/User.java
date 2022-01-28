package joon.homework.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Column
    private String givenName;

    @Column
    private String familyName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private String picture;

    @Builder

    public User(String givenName, String familyName, String email, String picture) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.email = email;
        this.picture = picture;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
