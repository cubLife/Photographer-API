package com.gmail.serhiisemiv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private int id;
    @NotBlank(message = "First name can't be empty")
    private String costumerFirstName;
    @NotBlank(message = "Last name can't be empty")
    private String costumerLastName;
    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email should be valid. For example - sample@gmail.com")
    private String costumerEmail;
    @NotBlank(message = "Phone number can't be empty")
    @Pattern(regexp = "^\\+(?:[0-9]●?){11}[0-9]$", message = "Please type valid phone number. For example +481234567890")
    private String costumerPhone;
    private long creationDate;
    @NotBlank(message = "Photo session name can't be empty")
    @Size(min = 10, max = 50, message = "Size should be between 10-50 characters")
    private String photoSessionName;
    private String orderStatus;
    private long startTime;
    private long endTime;
    private int costumerId;
    private int photoSessionPackageId;
    private String photoSessionPackageName;

}