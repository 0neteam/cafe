package com.java.cafe.service;

import com.java.cafe.entity.Board;
import com.java.cafe.repository.BoardRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CafeHomeServiceImp implements CafeHomeService {

    private final BoardRepository boardRepository;

    @Override
    public List<Board> homeCafeList(int type, char useYN) {
        Sort cafeListDesc = Sort.by(Sort.Order.desc("regDate")); 
        return boardRepository.findByTypeAndUseYN(type, useYN, cafeListDesc);
    }

    @Override
    public Board save(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public List<Board> searchCafeList(String keyward) {
        return boardRepository.findByNameContainingIgnoreCase(keyward);
    }

    @Override
    public Optional<Board> cafeBaseInfo(Integer type, String domain){
        return boardRepository.findByTypeAndDomain(type, domain);
    }

}
