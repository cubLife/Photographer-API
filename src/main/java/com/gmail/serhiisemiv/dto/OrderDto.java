package com.gmail.serhiisemiv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private int id;
    @NotBlank(message = "First name can't be empty")
    private String firstName;
    @NotBlank(message = "Last name can't be empty")
    private String lastName;
    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email should be valid. For example - sample@gmail.com")
    private String email;
    @NotBlank(message = "Phone number can't be empty")
    @Pattern(regexp = "^\\+(?:[0-9]●?){10}[0-9]$", message = "Please type valid phone number. For example +48123456789")
    private String phone;
    private long creationDate;
    private long photoSessionDate;
    private int photoSessionId;
    private int costumerId;
    @Min(value = 1, message = "Choose photo session package")
    private int photoSessionPackageId;
}