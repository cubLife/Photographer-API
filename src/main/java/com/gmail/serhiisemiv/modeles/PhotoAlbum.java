package com.gmail.serhiisemiv.modeles;

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
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;

    public PhotoAlbum() {
    }

    public PhotoAlbum(String name, List<Photo> photos, Photographer photographer) {
        this.name = name;
        this.photos = photos;
        this.photographer = photographer;
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

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Photographer getPhotographer() {
        return photographer;
    }

    public void setPhotographer(Photographer photographer) {
        this.photographer = photographer;
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
