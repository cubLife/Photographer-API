package com.gmail.serhiisemiv.modeles;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Builder
@Entity
@Table(name = "photo_sessions")
public class PhotoSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @OneToMany(mappedBy = "photoSession", cascade = CascadeType.ALL)
    private List<Order> orders;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photographer_id")
    private Photographer photographer;

    public PhotoSession() {
    }

    public PhotoSession(String name, List<Order> orders, Photographer photographer) {
        this.name = name;
        this.orders = orders;
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
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
        if (!(o instanceof PhotoSession)) return false;
        PhotoSession that = (PhotoSession) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "PhotoSession{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
