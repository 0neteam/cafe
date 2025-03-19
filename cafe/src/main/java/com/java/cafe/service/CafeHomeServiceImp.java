package com.java.cafe.service;

import com.java.cafe.entity.Board;
import com.java.cafe.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CafeHomeServiceImp implements CafeHomeService {

    private final BoardRepository boardRepository;

    @Override
    public List<Board> homeCafeList(int type) {
        return boardRepository.findByType(type);
    }


    @Override
    public Board save(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public List<Board> searchCafeList(String keyward) {
        return boardRepository.findByNameContainingIgnoreCase(keyward);
    }

}
