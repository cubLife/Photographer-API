package com.gmail.serhiisemiv.modeles;

import lombok.Builder;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
public class PhotoSessionPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int numberPhotos;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private int duration;
    @OneToMany(mappedBy = "photoSessionPackage",cascade = CascadeType.ALL)
    private List<Order> orders;

    public PhotoSessionPackage() {
    }

    public PhotoSessionPackage(int id, String name, int numberPhotos, int price, int duration, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.numberPhotos = numberPhotos;
        this.price = price;
        this.duration = duration;
        this.orders = orders;
    }

    public PhotoSessionPackage(int id, String name, int numberPhotos, int price, int duration) {
        this.id = id;
        this.name = name;
        this.numberPhotos = numberPhotos;
        this.price = price;
        this.duration = duration;
    }

    public PhotoSessionPackage(String name, int numberPhotos, int price, int duration) {
        this.name = name;
        this.numberPhotos = numberPhotos;
        this.price = price;
        this.duration = duration;
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

    public int getNumberPhotos() {
        return numberPhotos;
    }

    public void setNumberPhotos(int numberPhotos) {
        this.numberPhotos = numberPhotos;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoSessionPackage)) return false;
        PhotoSessionPackage that = (PhotoSessionPackage) o;
        return getId() == that.getId() && getNumberPhotos() == that.getNumberPhotos() && getPrice() == that.getPrice() && getDuration() == that.getDuration() && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getNumberPhotos(), getPrice(), getDuration());
    }

    @Override
    public String toString() {
        return "PhotoSessionPackage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberPhotos=" + numberPhotos +
                ", price=" + price +
                ", duration=" + duration +
                '}';
    }
}
