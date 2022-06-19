package com.gmail.serhiisemiv.modeles;

import lombok.Builder;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Objects;

@SuperBuilder
@Entity
@Table(name = "photos")
public class Photo extends Image {
    private String name;
    private Long size;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_album_id")
    private PhotoAlbum photoAlbum;

    public Photo() {
    }

    public Photo(int id, byte[] picture, String name, Long size, PhotoAlbum photoAlbum) {
        super(id, picture);
        this.name = name;
        this.size = size;
        this.photoAlbum = photoAlbum;
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

    public PhotoAlbum getPhotoAlbum() {
        return photoAlbum;
    }

    public void setPhotoAlbum(PhotoAlbum photoAlbum) {
        this.photoAlbum = photoAlbum;
    }

    protected Photo(int id, byte[] picture) {
        super(id, picture);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Photo{" +
                "name='" + name + '\'' +
                ", size=" + size +
                "} " + super.toString();
    }
}
