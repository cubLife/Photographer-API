package com.gmail.serhiisemiv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoAlbumDto {
    private int id;
    @NotBlank(message = "Name can't be empty")
    private String name;
    @Min(value = 1)
    private int photoSessionId;
}
