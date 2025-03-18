package com.java.cafe.service;

import com.java.cafe.dto.BoardDTO;
import com.java.cafe.entity.Board;
import com.java.cafe.repository.BoardRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CafeHomeService {

    private final BoardRepository boardRepository;

    public Board createBoard(BoardDTO boardDTO);
    Board board = Board.builder()
            .no(no)
            .regUserNo(regUserNo)
            .type(type)
            .name(name)
            .regDate(regDate)
            .domain(domain)
            .description(des)
            .useYN("y")
            .build();
    return boardRepository.save(board);
    }
}
