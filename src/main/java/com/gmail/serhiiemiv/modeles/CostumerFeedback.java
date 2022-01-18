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
@Table(name = "feedbacks")
public class CostumerFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private LocalDateTime dateTime;
    @Column(nullable = false)
    private String feedback;
    @Column(nullable = false)
    private Grade grade;
    private boolean isChanged;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "costumer_id", referencedColumnName = "id")
    private Costumer costumer;

    public CostumerFeedback(LocalDateTime dateTime, String feedback, Grade grade, Costumer costumer) {
        this.dateTime = dateTime;
        this.feedback = feedback;
        this.grade = grade;
        this.costumer = costumer;
    }

    public CostumerFeedback(int id, LocalDateTime dateTime, String feedback, Grade grade, Costumer costumer) {
        this.id = id;
        this.dateTime = dateTime;
        this.feedback = feedback;
        this.grade = grade;
        this.costumer = costumer;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CostumerFeedback)) return false;
        CostumerFeedback that = (CostumerFeedback) o;
        return id == that.id && isChanged == that.isChanged && Objects.equals(dateTime, that.dateTime) && Objects.equals(feedback, that.feedback) && grade == that.grade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, feedback, grade, isChanged);
    }
}
