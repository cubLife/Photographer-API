package com.gmail.serhiiemiv.modeles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Lob
    @Column(nullable = false, columnDefinition = "BLOB")
    private byte[] photo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_album_id", referencedColumnName = "id")
    private PhotoAlbum photoAlbum;

    public Photo(byte[] photo, PhotoAlbum photoAlbum) {
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
        Photo photo = (Photo) o;
        return id == photo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
