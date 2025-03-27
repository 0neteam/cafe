package com.java.cafe.service;

import com.java.cafe.dto.BoardDTO;
import org.springframework.ui.Model;

public interface CafeHomeService {

    public void searchCafeList(String keyword, Model model);
    public String save(BoardDTO boardDTO);
    
}
