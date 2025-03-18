package com.java.cafe.cafeEach;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "cafePost")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CafePost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @ManyToOne
    @JoinColumn(name="menuNo", referencedColumnName = "no", nullable = false)
    CafeMenu cafeMenu;

    @Column(nullable = false)
    private Integer writer;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Date firstDate;
    private Date lastDate;

}