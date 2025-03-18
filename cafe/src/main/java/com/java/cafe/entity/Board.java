package com.java.cafe.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "board")
@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private Date regDate;

    @Column(nullable = false, length = 30)
    private String domain;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private char useYN;

}
