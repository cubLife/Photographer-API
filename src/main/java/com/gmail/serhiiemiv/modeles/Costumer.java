package com.gmail.serhiiemiv.modeles;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity
@Table(name = "costumers")
public class Costumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private int phone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "costumer_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "costumer", cascade = CascadeType.ALL)
    private List<Order> orders;
    @OneToMany(mappedBy = "costumer", cascade = CascadeType.ALL)
    private List<CostumerFeedback> feedbacks;

    public Costumer(String firstName, String lastName, String email, int phone, List<Order> orders) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.orders = orders;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Costumer)) return false;
        Costumer costumer = (Costumer) o;
        return id == costumer.id && phone == costumer.phone && Objects.equals(firstName, costumer.firstName) && Objects.equals(lastName, costumer.lastName) && Objects.equals(email, costumer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, phone);
    }
}
