package com.java.cafe.dto;

import com.java.cafe.entity.Menu;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

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
    private Integer regUserNo;
    private LocalDateTime regDate;
    private Integer modUserNo;
    private LocalDateTime modDate;
    private Integer viewCount;
    private char useYN;

}
