package com.gmail.serhiisemiv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostumerDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    long birthDay;
}
