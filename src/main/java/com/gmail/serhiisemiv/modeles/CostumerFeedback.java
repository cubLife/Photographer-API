package com.gmail.serhiisemiv.modeles;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Builder
@Entity
@Table(name = "feedbacks")
public class CostumerFeedback {
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
    private Long creationDate;
    @Column(nullable = false)
    private String feedback;
    @Column(nullable = false)
    private int grade;

    public CostumerFeedback() {
    }

    public CostumerFeedback(String firstName, String lastName,String email , Long creationDate, String feedback, int grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.creationDate = creationDate;
        this.feedback = feedback;
        this.grade = grade;
        this.email=email;
    }

    public CostumerFeedback(int id, String firstName, String lastName, String email, Long creationDate, String feedback, int grade) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email=email;
        this.creationDate = creationDate;
        this.feedback = feedback;
        this.grade = grade;
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

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CostumerFeedback)) return false;
        CostumerFeedback that = (CostumerFeedback) o;
        return getId() == that.getId() && getGrade() == that.getGrade() && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getCreationDate(), that.getCreationDate()) && Objects.equals(getFeedback(), that.getFeedback());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getEmail(), getCreationDate(), getFeedback(), getGrade());
    }

    @Override
    public String toString() {
        return "CostumerFeedback{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", creationDate=" + creationDate +
                ", feedback='" + feedback + '\'' +
                ", grade=" + grade +
                ", email=" + email +
                '}';
    }
}
