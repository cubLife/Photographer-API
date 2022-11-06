package com.gmail.serhiisemiv.modeles;

import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@SuperBuilder
@Entity
@Table(name = "costumers")
public class Costumer  extends User{
    private long birthDay;
    @OneToMany(mappedBy = "costumer", cascade = CascadeType.ALL)
    private List<Order> orders;

    public Costumer() {
    }

    public Costumer(int id, String login, String password, String firstName, String lastName, String email, String phone, long birthDay, List<Order> orders, List<CostumerFeedback> feedbacks) {
        super(id, login, password, firstName, lastName, email, phone);
        this.birthDay=birthDay;
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public long getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(long birthDay) {
        this.birthDay = birthDay;
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
