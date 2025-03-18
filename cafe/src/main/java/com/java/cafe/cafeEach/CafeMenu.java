package com.java.cafe.cafeEach;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "cafeMenu")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CafeMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(nullable = false, length = 30)
    private String grp;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private Integer orderNo;

}
