package com.java.cafe.service;

import com.java.cafe.entity.Board;

import java.util.List;
import java.util.Optional;

public interface CafeHomeService {

    public Board save(Board board);
    public List<Board> homeCafeList(int type);
    public List<Board> searchCafeList(String keyward);
    public Optional<Board> cafeBaseInfo(Integer type, String domain);
}
