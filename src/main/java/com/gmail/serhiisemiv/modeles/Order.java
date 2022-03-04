package com.gmail.serhiisemiv.modeles;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(nullable = false)
    private long creationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_session_id", referencedColumnName = "id")
    private PhotoSession photoSession;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "costumer_id", referencedColumnName = "id")
    private Costumer costumer;

    public Order() {
    }

    public Order(Long creationDate, PhotoSession photoSession, Costumer costumer) {
        this.creationDate = creationDate;
        this.photoSession = photoSession;
        this.costumer = costumer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public PhotoSession getPhotoSession() {
        return photoSession;
    }

    public void setPhotoSession(PhotoSession photoSession) {
        this.photoSession = photoSession;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(creationDate, order.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dateTime=" + creationDate +
                ", photoSession=" + photoSession +
                ", costumer=" + costumer +
                '}';
    }
}
