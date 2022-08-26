package com.gmail.serhiisemiv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostumerFeedbackDto {
    private int id;
    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email should be valid. For example - sample@gmail.com")
    String costumerEmail;
    String costumerFirstName;
    private Long creationDate;
    @NotBlank(message = "Feedback can't be empty")
    private String feedback;
    private int grade;
    private boolean isChanged;
    private int costumerId;
}
