package com.java.cafe.dto;

import com.java.cafe.entity.Menu;
import lombok.*;

import java.util.List;

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
    private char useYN;
    private List<Menu> children;

}
