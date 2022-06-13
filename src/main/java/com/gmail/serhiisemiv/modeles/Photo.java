package com.gmail.serhiisemiv.modeles;

import lombok.Builder;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Objects;

@Builder
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Long size;
    @Lob
    @Column(nullable = false)
    private byte[] image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_album_id")
    private PhotoAlbum photoAlbum;

    public Photo() {
    }

    public Photo(String name, Long size, byte[] image) {
        this.name = name;
        this.size = size;
        this.image = image;
    }

    public Photo(int id, String name, Long size, byte[] image, PhotoAlbum photoAlbum) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.image = image;
        this.photoAlbum = photoAlbum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public PhotoAlbum getPhotoAlbum() {
        return photoAlbum;
    }

    public void setPhotoAlbum(PhotoAlbum photoAlbum) {
        this.photoAlbum = photoAlbum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Photo)) return false;
        Photo photo1 = (Photo) o;
        return id == photo1.id && Objects.equals(name, photo1.name) && Objects.equals(size, photo1.size) && Arrays.equals(image, photo1.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, size);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", image=" + Arrays.toString(image) +
                ", photoAlbum=" + photoAlbum +
                '}';
    }
}
