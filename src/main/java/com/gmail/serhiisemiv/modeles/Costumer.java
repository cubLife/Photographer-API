package com.gmail.serhiisemiv.modeles;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@SuperBuilder
@Entity
@Table(name = "costumers")
public class Costumer  extends User{
    @OneToMany(mappedBy = "costumer", cascade = CascadeType.ALL)
    private List<Order> orders;
    @OneToMany(mappedBy = "costumer", cascade = CascadeType.ALL)
    private List<CostumerFeedback> feedbacks;

    public Costumer() {
    }

    public Costumer(int id, String login, String password, String firstName, String lastName, String email, int phone, List<Order> orders, List<CostumerFeedback> feedbacks) {
        super(id, login, password, firstName, lastName, email, phone);
        this.orders = orders;
        this.feedbacks = feedbacks;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<CostumerFeedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<CostumerFeedback> feedbacks) {
        this.feedbacks = feedbacks;
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
        return "Costumer{} " + super.toString();
    }
}
