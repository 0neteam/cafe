package com.java.cafe.service;

import com.java.cafe.dto.MenuDTO;
import com.java.cafe.entity.Board;
import com.java.cafe.entity.Menu;
import com.java.cafe.repository.BoardRepository;
import com.java.cafe.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CafeEachServiceImp implements CafeEachService {

    private final MenuRepository menuRepository;
    private final BoardRepository boardRepository;

    public List<MenuDTO> getMenuList(String domain) {
        System.out.println("+++++++++++++++++++++++++++" + domain);
        Optional<Board> boardSelect = boardRepository.findByTypeAndDomain(1, domain);
        if (boardSelect.isEmpty()) {
            throw new IllegalArgumentException("Board not found for domain: " + domain);
        }
        Board board = boardSelect.get();
        Integer boardNo = board.getNo();

        System.out.println(boardNo);
        List<Menu> menus = menuRepository.findByBoardNoAndRefAndUseYNOrderByOrderNoAsc(boardNo, 0, 'Y');
        System.out.println(menus);
        List<MenuDTO> filterMenus = new ArrayList<>();

        for (Menu menu : menus) {
            List<Menu> filteredChildren = new ArrayList<>();
            for (Menu child : menu.getChildren()) {
                if (child.getUseYN() == 'Y') {
                    filteredChildren.add(child);
                }
            }
            filterMenus.add(new MenuDTO(
                    menu.getNo(),
                    menu.getBoard().getNo(),
                    menu.getOrderNo(),
                    menu.getDepth(),
                    menu.getName(),
                    menu.getRef(),
                    menu.getUseYN(),
                    filteredChildren // 필터링된 children 리스트
            ));
        }
        return filterMenus;
    }


    public MenuDTO createMenu(String domain, MenuDTO menuDTO) {

        Optional<Board> boardSelect = boardRepository.findByTypeAndDomain(1, domain);
        if (boardSelect.isEmpty()) {
            throw new IllegalArgumentException("Board not found for domain: " + domain);
        }
        Board board = boardSelect.get();

        Menu menu = Menu.builder()
                .board(board)
                .orderNo(1)
                .depth(menuDTO.getDepth())
                .name(menuDTO.getName())
                .ref(menuDTO.getRef())
                .useYN('Y')
                .build();
        menuRepository.save(menu);
        return menuDTO;
    }

    // 메뉴 수정
    public MenuDTO editMenu(String domain, MenuDTO menuDTO) {
        Menu menu = menuRepository.findById(menuDTO.getNo())
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        menu.setName(menuDTO.getName());
        menu.setRef(menuDTO.getRef());
        menuRepository.save(menu);
        return menuDTO;
    }

    // 메뉴 삭제
    public String deleteMenu(String domain, MenuDTO menuDTO) {
        Menu menu = menuRepository.findById(menuDTO.getNo())
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        menu.setUseYN('N');
        menuRepository.save(menu);
        return "success";
    }

}
