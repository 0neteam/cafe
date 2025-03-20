package com.java.cafe.service;

import com.java.cafe.dto.BoardDTO;
import com.java.cafe.entity.Board;
import com.java.cafe.repository.BoardRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CafeTempServiceImp implements CafeTempService {

    private final BoardRepository boardRepository;

    @Override
    public BoardDTO cafeInfo(int type, String domain) {
        Board board = boardRepository.findByTypeAndDomain(type, domain)
                .orElseThrow(() -> new RuntimeException("도메인 정보를 찾을 수 없음"));
        BoardDTO boardDTO = new BoardDTO(
                board.getNo(),
                board.getRegUserNo(),
                board.getType(),
                board.getName(),
                board.getRegDate(),
                board.getDomain(),
                board.getDescription(),
                board.getUseYN()
        );
        return boardDTO;
    }

}
