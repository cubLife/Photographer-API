package com.gmail.serhiisemiv.modeles;
import javax.persistence.*;

@Entity
@Table(name = "avatar_images")
public class AvatarImage extends Image{
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photographer_id", nullable = false)
    private Photographer photographer;

    public AvatarImage(){
    }

    public AvatarImage(byte[] picture, Photographer photographer) {
        super(picture);
        this.photographer = photographer;
    }

    public AvatarImage(int id, byte[] picture, Photographer photographer) {
        super(id, picture);
        this.photographer = photographer;
    }

    public Photographer getPhotographer() {
        return photographer;
    }

    public void setPhotographer(Photographer photographer) {
        this.photographer = photographer;
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
