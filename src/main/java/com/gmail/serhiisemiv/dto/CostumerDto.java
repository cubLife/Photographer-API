package com.gmail.serhiisemiv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostumerDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
