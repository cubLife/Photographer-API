package com.gmail.serhiisemiv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoSessionPackageDto {
    private int id;
    private String name;
    private int numberPhotos;
    private int price;
    private int duration;
}
