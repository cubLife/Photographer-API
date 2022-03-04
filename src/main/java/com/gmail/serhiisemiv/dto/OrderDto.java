package com.gmail.serhiisemiv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private int id;
    private Long creationDate;
    private int photoSessionId;
    private int costumerId;
}
