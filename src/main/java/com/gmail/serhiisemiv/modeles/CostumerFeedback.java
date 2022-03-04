package com.gmail.serhiisemiv.modeles;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@Builder
@Entity
@Table(name = "feedbacks")
public class CostumerFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private Long creationDate;
    @Column(nullable = false)
    private String feedback;
    @Column(nullable = false)
    private Grade grade;
    private boolean isChanged;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "costumer_id", referencedColumnName = "id")
    private Costumer costumer;

    public CostumerFeedback() {
    }

    public CostumerFeedback(Long creationDate, String feedback, Grade grade, Costumer costumer) {
        this.creationDate = creationDate;
        this.feedback = feedback;
        this.grade = grade;
        this.costumer = costumer;
    }

    public CostumerFeedback(int id, Long creationDate, String feedback, Grade grade, Costumer costumer) {
        this.id = id;
        this.creationDate = creationDate;
        this.feedback = feedback;
        this.grade = grade;
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

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setIsChanged(boolean changed) {
        isChanged = changed;
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
        if (!(o instanceof CostumerFeedback)) return false;
        CostumerFeedback that = (CostumerFeedback) o;
        return id == that.id && isChanged == that.isChanged && Objects.equals(creationDate, that.creationDate) && Objects.equals(feedback, that.feedback) && grade == that.grade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate, feedback, grade, isChanged);
    }

    @Override
    public String toString() {
        return "CostumerFeedback{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", feedback='" + feedback + '\'' +
                ", grade=" + grade +
                ", isChanged=" + isChanged +
                ", costumer=" + costumer +
                '}';
    }
}
