package com.kredx.moneylending.dto;

import com.kredx.moneylending.entity.User;
import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LogInDto {

    private String username;
    private String password;
}
