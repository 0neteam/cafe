package com.java.cafe.cafehome;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "cafe")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 30)
    private String domain;

    @Column(nullable = false, length = 30)
    private String host;

    @Column(nullable = false)
    private String des;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false, columnDefinition = "CHAR")
    private String useYN;

}
