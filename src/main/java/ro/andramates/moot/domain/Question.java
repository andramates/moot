package ro.andramates.moot.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "questions")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(name = "question_date", nullable = false, unique = true)
    private LocalDate questionDate;


    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}