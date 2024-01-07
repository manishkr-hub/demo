package com.kredx.moneylending.dto;

import com.kredx.moneylending.entity.User;
import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

    private String name;
    private String username;
    private String password;

    public User toUser() {
        return User.builder()
                .name(name)
                .username(username)
                .build();
    }
}
