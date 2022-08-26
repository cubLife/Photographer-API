package com.gmail.serhiisemiv.modeles;

import javax.persistence.*;

@Entity
@Table(name = "carousel_image")
public class CarouselImage  extends Image {

    public CarouselImage() {
        super();
    }

    public CarouselImage(byte[] picture) {
        super(picture);
    }

    public CarouselImage(int id, byte[] picture) {
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
        return super.toString();
    }
}