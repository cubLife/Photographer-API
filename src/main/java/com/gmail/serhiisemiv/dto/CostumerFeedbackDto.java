package com.gmail.serhiisemiv.dto;

import com.gmail.serhiisemiv.modeles.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostumerFeedbackDto {
    private int id;
    private Long creationDate;
    private String feedback;
    private Grade grade;
    private boolean isChanged;
    private int costumerId;
}
