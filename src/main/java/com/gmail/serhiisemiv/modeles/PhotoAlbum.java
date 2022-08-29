package com.gmail.serhiisemiv.modeles;

import com.gmail.serhiisemiv.repository.PhotoSessionIconRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Builder
@Entity
@Table(name = "photo_albums")
public class PhotoAlbum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "photoAlbum", cascade = CascadeType.ALL)
    private List<Photo> photos;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_album_id")
    private PhotoSession photoSession;

    public PhotoAlbum() {
    }

    public PhotoAlbum(String name, PhotoSession photoSession) {
        this.name = name;
        this.photoSession = photoSession;
    }

    public PhotoAlbum(String name, PhotoSession photoSession, List<Photo> photos) {
        this.name = name;
        this.photos = photos;
        this.photoSession = photoSession;
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

    public List<Photo> getPhotos() {
        return photos;
    }

    public PhotoSession getPhotoSession() {
        return photoSession;
    }

    public void setPhotoSession(PhotoSession photoSession) {
        this.photoSession = photoSession;
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

    @Override
    public String toString() {
        return "PhotoAlbum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
