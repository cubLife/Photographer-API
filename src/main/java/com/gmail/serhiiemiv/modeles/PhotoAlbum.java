package com.gmail.serhiiemiv.modeles;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity
@Table(name = "photo_albums")
public class PhotoAlbum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "photoAlbum", cascade = CascadeType.ALL)
    private List<Photo> photos;

    public PhotoAlbum(String name, List<Photo> photos) {
        this.name = name;
        this.photos = photos;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoAlbum)) return false;
        PhotoAlbum that = (PhotoAlbum) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
