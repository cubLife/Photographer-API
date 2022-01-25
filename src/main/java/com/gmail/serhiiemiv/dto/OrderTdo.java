package com.gmail.serhiiemiv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderTdo {
    private int id;
    private LocalDateTime dateTime;
    private int photoSessionId;
    private int costumerId;
}
