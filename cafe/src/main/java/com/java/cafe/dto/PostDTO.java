package com.java.cafe.dto;

import com.java.cafe.entity.Menu;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {

    private Integer no;
    private Integer menuNo;
    private String title;
    private String content;
    private Date regUserNo;
    private Date regDate;
    private Date modUserNo;
    private Date modDate;
    private Integer viewCount;
    private char useYN;

}
