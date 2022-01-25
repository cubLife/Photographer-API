package com.gmail.serhiiemiv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTdo {
    private int id;
    private String login;
    private String password;
    private int costumerId;
}
