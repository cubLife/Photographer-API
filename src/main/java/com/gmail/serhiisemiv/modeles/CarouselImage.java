package com.gmail.serhiisemiv.modeles;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "carousel_image")
public class CarouselImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
    @Lob
    @Column(nullable = false)
   private byte[] image;

    public CarouselImage() {
    }

    public CarouselImage(int id, byte[] image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarouselImage)) return false;
        CarouselImage that = (CarouselImage) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "CarouselImage{" +
                "id=" + id +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
