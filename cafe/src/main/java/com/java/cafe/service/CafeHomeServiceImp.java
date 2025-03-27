package com.java.cafe.service;

import com.java.cafe.dto.BoardDTO;
import com.java.cafe.entity.Board;
import com.java.cafe.entity.User;
import com.java.cafe.repository.BoardRepository;
import com.java.cafe.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CafeHomeServiceImp implements CafeHomeService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    private final int TYPE = 1;
    private final char useYN = 'Y';

    //카페 만들기
    @Override
    public String save(BoardDTO boardDTO) {
        try {
            User user = userRepository.findById(1).orElseThrow();
            Board board = Board.builder()
                .regUserNo(user.getNo())
                .type(TYPE)
                .name(boardDTO.getName())
                .domain(boardDTO.getDomain())
                .description(boardDTO.getDescription())
                .regDate(LocalDate.now())
                .useYN(useYN)
                .user(user)
                .build();
            boardRepository.save(board);
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }

    //카페 리스트 (검색, 조회)
    @Override
    public void searchCafeList(String keyword, Model model) {
        if(keyword == null) {
            Sort cafeListDesc = Sort.by(Sort.Order.desc("regDate"));
            List<Board> cafeList = boardRepository.findByTypeAndUseYN(TYPE, useYN, cafeListDesc);
            model.addAttribute("cafeList", cafeList);
        } else {
            List<Board> cafeList = boardRepository.findByTypeAndUseYNAndNameContainingIgnoreCaseOrderByRegDateDesc(TYPE, useYN, keyword);
            model.addAttribute("cafeList", cafeList);
            model.addAttribute("keyword", keyword);
        }
    }

}
