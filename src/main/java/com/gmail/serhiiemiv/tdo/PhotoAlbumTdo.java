package com.gmail.serhiiemiv.tdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoAlbumTdo {
    private int id;
    private String name;
    private List<Integer> photosId;
}
