package com.gmail.serhiisemiv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoSessionDto {
    private int id;
    private String name;
    private String type;
    private int price;
    private int duration;
    private List<Integer> ordersId;
}
