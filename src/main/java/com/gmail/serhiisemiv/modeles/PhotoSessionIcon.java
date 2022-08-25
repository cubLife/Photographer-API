package com.gmail.serhiisemiv.modeles;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PhotoSessionIcon extends Image{
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photographer_id", nullable = false)
    private PhotoSession photoSession;

    public PhotoSessionIcon() {
    }

    public PhotoSession getPhotoSession() {
        return photoSession;
    }

    public void setPhotoSession(PhotoSession photoSession) {
        this.photoSession = photoSession;
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
