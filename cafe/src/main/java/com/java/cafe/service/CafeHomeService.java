package com.java.cafe.service;

import com.java.cafe.dto.BoardDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

public interface CafeHomeService {

    public void searchCafeList(String keyword, Model model);
    public String save(BoardDTO boardDTO, HttpServletRequest req);

}
