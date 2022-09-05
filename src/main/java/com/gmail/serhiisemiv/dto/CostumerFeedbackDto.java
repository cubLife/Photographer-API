package com.gmail.serhiisemiv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CostumerFeedbackDto {
    private int id;
    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email should be valid. For example - sample@gmail.com")
    String email;
    @NotBlank(message = "First name can't be empty")
    @Size(min = 4, max = 20, message = "First name should be between 4-20 characters")
    String firstName;
    @NotBlank(message = "Last name can't be empty")
    @Size(min = 4, max = 20, message = "Last name should be between 4-20 characters")
    String lastName;
    private Long creationDate;
    @NotBlank(message = "Feedback can't be empty")
    private String feedback;
    private int grade;

}
