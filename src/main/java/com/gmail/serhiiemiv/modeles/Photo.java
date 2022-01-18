package com.gmail.serhiiemiv.modeles;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@NoArgsConstructor
@Builder
@Getter
@ToString
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Long size;
    @Lob
    @Column(nullable = false, columnDefinition = "BLOB")
    private byte[] photo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_album_id", referencedColumnName = "id")
    private PhotoAlbum photoAlbum;

    public Photo(String name, Long size, byte[] photo) {
        this.name = name;
        this.size = size;
        this.photo = photo;
    }

    public Photo(int id, String name, Long size, byte[] photo, PhotoAlbum photoAlbum) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.photo = photo;
        this.photoAlbum = photoAlbum;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setPhotoAlbum(PhotoAlbum photoAlbum) {
        this.photoAlbum = photoAlbum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;
        Photo photo1 = (Photo) o;
        return id == photo1.id && Objects.equals(name, photo1.name) && Objects.equals(size, photo1.size) && Arrays.equals(photo, photo1.photo);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, size);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }
}
