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
    private String name;
    private String domain;
    private String host;
    private String des;
    private Date date;
    private String yn;

}
