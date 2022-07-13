package com.gmail.serhiisemiv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostumerFeedbackDto {
    private int id;
    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email should be valid. For example - sample@gmail.com")
    String email;
    private Long creationDate;
    @NotBlank(message = "Feedback can't be empty")
    @Size(min = 30, message = "Feedback too small")
    private String feedback;
    private int grade;
    private boolean isChanged;
    private int costumerId;
}
