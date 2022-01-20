package com.gmail.serhiiemiv.tdo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoTdo {
    private int id;
    private String name;
    private Long size;
    private byte[] image;
    private int photoAlbumId;
}
