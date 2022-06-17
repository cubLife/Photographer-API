package com.gmail.serhiisemiv.modeles;

import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@SuperBuilder
@Entity
@Table(name = "photographers")
public class Photographer extends User {
    @Lob
    private byte[] avatarImage;
    private String aboutMyself;
    @OneToMany(mappedBy = "photographer", cascade = CascadeType.ALL)
    private List<SocialNetwork> socialNetNetworks;
    @OneToMany(mappedBy = "photographer", cascade = CascadeType.ALL)
    private List<PhotoSession> photoSessions;
    @OneToMany(mappedBy = "photographer", cascade = CascadeType.ALL)
    private List<PhotoAlbum> photoAlbums;

    public Photographer() {
    }

    private Photographer(int id, String login, String password, String firstName, String lastName, String email, int phone
            , byte[] avatarImage, String aboutMyself, List<SocialNetwork> socialNetNetworks, List<PhotoSession> photoSessions
            , List<PhotoAlbum> photoAlbums) {
        super(id, login, password, firstName, lastName, email, phone);
        this.avatarImage = avatarImage;
        this.aboutMyself = aboutMyself;
        this.socialNetNetworks = socialNetNetworks;
        this.photoSessions = photoSessions;
        this.photoAlbums = photoAlbums;
    }

    public byte[] getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(byte[] avatarImage) {
        this.avatarImage = avatarImage;
    }

    public String getAboutMyself() {
        return aboutMyself;
    }

    public void setAboutMyself(String aboutMyself) {
        this.aboutMyself = aboutMyself;
    }

    public List<SocialNetwork> getSocialNetNetworks() {
        return socialNetNetworks;
    }

    public void setSocialNetNetworks(List<SocialNetwork> socialNetNetworks) {
        this.socialNetNetworks = socialNetNetworks;
    }

    public List<PhotoSession> getPhotoSessions() {
        return photoSessions;
    }

    public void setPhotoSessions(List<PhotoSession> photoSessions) {
        this.photoSessions = photoSessions;
    }

    public List<PhotoAlbum> getPhotoAlbums() {
        return photoAlbums;
    }

    public void setPhotoAlbums(List<PhotoAlbum> photoAlbums) {
        this.photoAlbums = photoAlbums;
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
        return super.toString() +
                "Photographer{" +
                "aboutMyself='" + aboutMyself + '\'' +
                "} ";
    }
}
