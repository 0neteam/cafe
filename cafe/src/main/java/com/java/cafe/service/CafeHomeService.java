package com.java.cafe.service;

import com.java.cafe.entity.Board;

import java.util.List;

public interface CafeHomeService {

    public Board save(Board board);
    public List<Board> homeCafeList(int type);
    public List<Board> searchCafeList(String keyward);
}
