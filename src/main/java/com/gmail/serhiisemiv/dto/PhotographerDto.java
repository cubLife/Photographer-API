package com.gmail.serhiisemiv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotographerDto {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private long phone;
    private String aboutMyself;
}
