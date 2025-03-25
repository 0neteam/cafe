package com.java.cafe.service;

import com.java.cafe.dto.BoardDTO;
import com.java.cafe.entity.Board;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;


public interface CafeHomeService {

    public void searchCafeList(String keyword, Model model);
    public String save(BoardDTO boardDTO);
    
}
