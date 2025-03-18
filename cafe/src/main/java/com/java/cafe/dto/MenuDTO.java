package com.java.cafe.dto;

import com.java.cafe.entity.Board;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuDTO {

    private Integer no;
    private Integer boardNo;
    private Integer orderNo;
    private Integer depth;
    private String name;
    private Integer ref;
    private Integer useYN;

}
