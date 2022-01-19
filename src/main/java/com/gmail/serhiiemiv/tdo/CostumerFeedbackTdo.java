package com.gmail.serhiiemiv.tdo;

import com.gmail.serhiiemiv.modeles.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostumerFeedbackTdo {
    private int id;
    private LocalDateTime dateTime;
    private String feedback;
    private Grade grade;
    private boolean isChanged;
    private int costumerId;
}
