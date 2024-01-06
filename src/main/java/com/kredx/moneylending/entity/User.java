package com.kredx.moneylending.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder(toBuilder = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

}
