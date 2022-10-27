package com.gmail.serhiisemiv.modeles;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "socialNetworks")
public class SocialNetwork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String link;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photographer_Id")
    private Photographer photographer;

    public SocialNetwork() {
    }

    public SocialNetwork(String name, String link, Photographer photographer) {
        this.name = name;
        this.link = link;
        this.photographer = photographer;
    }

    public SocialNetwork(int id, String name, String link, Photographer photographer) {
        this.id = id;
        this.name = name;
        this.link = link;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
        if (!(o instanceof SocialNetwork)) return false;
        SocialNetwork that = (SocialNetwork) o;
        return getId() == that.getId() && Objects.equals(getName(), that.getName()) && Objects.equals(getLink(), that.getLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLink());
    }

    @Override
    public String toString() {
        return "SocialNetwork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
