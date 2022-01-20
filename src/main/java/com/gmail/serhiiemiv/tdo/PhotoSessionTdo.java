package com.gmail.serhiiemiv.tdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoSessionTdo {
    private int id;
    private String name;
    private String type;
    private int price;
    private int duration;
    private List<Integer> ordersId;
}
