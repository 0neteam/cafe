package com.java.cafe.service;

import com.java.cafe.dto.MenuDTO;
import com.java.cafe.entity.Board;
import com.java.cafe.entity.Menu;
import com.java.cafe.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CafeEachServiceImp implements CafeEachService {

    private final MenuRepository menuRepository;

    public List<MenuDTO> getMenuList(Integer boardNo) {
        List<Menu> menus = menuRepository.findByBoardNoAndRefAndUseYNOrderByOrderNoAsc(boardNo, 0, 'Y');
        System.out.println(menus);
        List<MenuDTO> menuDTOList = new ArrayList<>();
        for (Menu menu : menus) {
            menuDTOList.add(new MenuDTO(menu.getNo(),
                    menu.getBoard().getNo(),
                    menu.getOrderNo(),
                    menu.getDepth(),
                    menu.getName(),
                    menu.getRef(),
                    menu.getUseYN(),
                    menu.getChildren()));
        }
        return menuDTOList;
    }

    public MenuDTO createMenu(MenuDTO menuDTO) {

        System.out.println(menuDTO);
        Board board = new Board();
        board.setNo(2);

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
    public MenuDTO editMenu(MenuDTO menuDTO) {
        // 메뉴 ID로 찾아서 수정
        Menu menu = menuRepository.findById(menuDTO.getNo())
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        menu.setName(menuDTO.getName());  // 메뉴 이름 수정
        menu.setUseYN(menuDTO.getUseYN()); // 필요하면 다른 필드도 수정
        menuRepository.save(menu);
        return menuDTO;
    }

    // 메뉴 삭제
    public String deleteMenu(MenuDTO menuDTO) {
        Menu menu = menuRepository.findById(menuDTO.getNo())
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        menu.setUseYN('N');
        menuRepository.save(menu);
        return "success";
    }

}
