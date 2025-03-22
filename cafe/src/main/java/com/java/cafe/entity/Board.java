package com.java.cafe.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "board")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(nullable = false)
    private Integer regUserNo;

    @Column(nullable = false)
    private Integer type;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy.MM.dd.")
    private LocalDate regDate;

    @Column(nullable = false, length = 30)
    private String domain;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private char useYN;

}
