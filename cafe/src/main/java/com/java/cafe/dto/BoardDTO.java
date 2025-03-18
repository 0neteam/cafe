package com.java.cafe.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

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
    private Date regDate;
    private String domain;
    private String description;
    private char useYN;

}
