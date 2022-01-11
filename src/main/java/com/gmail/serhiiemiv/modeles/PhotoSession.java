package com.gmail.serhiiemiv.modeles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "photo_sessions")
public class PhotoSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private int duration;
    @OneToMany(mappedBy = "photoSession", cascade = CascadeType.ALL)
    private List<Order> orders;

    public PhotoSession(String name, String type, int price, int duration, List<Order> orders) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.duration = duration;
        this.orders = orders;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoSession)) return false;
        PhotoSession that = (PhotoSession) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
