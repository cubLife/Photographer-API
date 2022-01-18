package com.gmail.serhiiemiv.modeles;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(nullable = false)
    private LocalDateTime dateTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_session_id", referencedColumnName = "id")
    private PhotoSession photoSession;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "costumer_id", referencedColumnName = "id")
    private Costumer costumer;

    public Order(LocalDateTime dateTime, PhotoSession photoSession, Costumer costumer) {
        this.dateTime = dateTime;
        this.photoSession = photoSession;
        this.costumer = costumer;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setPhotoSession(PhotoSession photoSession) {
        this.photoSession = photoSession;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(dateTime, order.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime);
    }
}
