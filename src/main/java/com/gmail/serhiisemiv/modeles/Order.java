package com.gmail.serhiisemiv.modeles;

import com.gmail.serhiisemiv.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private long creationDate;
    private long startTime;
    private long endTime;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private String photoSessionName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="photo_session_package_id", referencedColumnName = "id")
    private PhotoSessionPackage photoSessionPackage;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "costumer_id", referencedColumnName = "id")
    private Costumer costumer;

    public Order() {
    }

    public Order(Long creationDate, Long startTime, Long endTime, OrderStatus orderStatus, String photoSessionMame, Costumer costumer, PhotoSessionPackage photoSessionPackage) {
        this.creationDate = creationDate;
        this.startTime=startTime;
        this.endTime=endTime;
        this.orderStatus = orderStatus;
      this.photoSessionName=photoSessionMame;
        this.costumer = costumer;
        this.photoSessionPackage=photoSessionPackage;
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

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPhotoSessionName() {
        return photoSessionName;
    }

    public void setPhotoSessionName(String photoSessionName) {
        this.photoSessionName = photoSessionName;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public PhotoSessionPackage getPhotoSessionPackage() {
        return photoSessionPackage;
    }

    public void setPhotoSessionPackage(PhotoSessionPackage photoSessionPackage) {
        this.photoSessionPackage = photoSessionPackage;
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
                ", creationDate=" + creationDate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", orderStatus=" + orderStatus +
                ", photoSessionName=" + photoSessionName +
                ", photoSessionPackage=" + photoSessionPackage +
                ", costumer=" + costumer +
                '}';
    }
}
