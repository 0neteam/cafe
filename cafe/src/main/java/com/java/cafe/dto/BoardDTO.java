package com.java.cafe.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {

    private Integer no;
    private Integer regUserNo;
    private Integer type;
    private String name;
    private LocalDate regDate;
    private String domain;
    private String description;
    private char useYN;

}
