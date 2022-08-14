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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private long creationDate;
    @Column(nullable = false)
    private long photoSessionDate;
    @Column(nullable = false)
    private boolean isComplete;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "photo_session_id", referencedColumnName = "id")
    private PhotoSession photoSession;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="photo_session_package_id", referencedColumnName = "id")
    private PhotoSessionPackage photoSessionPackage;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "costumer_id", referencedColumnName = "id")
    private Costumer costumer;

    public Order() {
    }

    public Order(Long creationDate, Long photoSessionDate, boolean isComplete, PhotoSession photoSession, Costumer costumer, PhotoSessionPackage photoSessionPackage) {
        this.creationDate = creationDate;
        this.photoSessionDate=photoSessionDate;
        this.isComplete = isComplete;
        this.photoSession = photoSession;
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

    public long getPhotoSessionDate() {
        return photoSessionDate;
    }

    public void setPhotoSessionDate(long photoSessionDate) {
        this.photoSessionDate = photoSessionDate;
    }


    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
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
                ", photoSessionDate=" + photoSessionDate +
                ", photoSession=" + photoSession +
                ", isComplete=" + isComplete +
                ", photoSessionPackage=" + photoSessionPackage +
                ", costumer=" + costumer +
                '}';
    }
}
