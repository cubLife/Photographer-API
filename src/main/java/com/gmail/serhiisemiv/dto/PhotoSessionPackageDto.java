package com.gmail.serhiisemiv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoSessionPackageDto {
    private int id;
    @NotBlank(message = "Name Can't be empty")
    @NotNull(message = "Name cannot be null")
    private String name;
    @Min(value = 1, message = "Number Photos should not be less than 1")
    @Positive(message = "Number Photos should be positive")
    private int numberPhotos;
    @Min(value = 1, message = "Price should not be less than 1")
    @Positive(message = "Price should be positive")
    private int price;
    @Min(value = 1, message = "Duration should not be less than 1")
    @Positive(message = "Duration Photos should be positive")
    private int duration;
}
