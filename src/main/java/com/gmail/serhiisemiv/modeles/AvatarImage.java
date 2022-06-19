package com.gmail.serhiisemiv.modeles;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "avatar_images")
public class AvatarImage extends Image{
    @OneToOne(mappedBy = "avatarImage")
    private Photographer photographer;

    public AvatarImage() {
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
