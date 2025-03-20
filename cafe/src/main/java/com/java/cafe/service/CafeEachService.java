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
public class CafeEachService {

    private final MenuRepository menuRepository;

    public List<MenuDTO> getMenuList(Integer boardNo) {
        List<Menu> menus = menuRepository.findByBoardNoAndRefAndUseYN(boardNo, 0, 'Y');
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

    public void createMenu(MenuDTO menuDTO) {

        System.out.println(menuDTO);
        Board board = new Board();
        board.setNo(menuDTO.getBoardNo());

        Menu menu = Menu.builder()
                .board(board)
                .orderNo(menuDTO.getOrderNo())
                .depth(menuDTO.getDepth())
                .name(menuDTO.getName())
                .ref(menuDTO.getRef())
                .useYN(menuDTO.getUseYN())
                .build();

        menuRepository.save(menu);
    }

    // 메뉴 수정
    public void editMenu(MenuDTO menuDTO) {
        // 메뉴 ID로 찾아서 수정
        Menu menu = menuRepository.findById(menuDTO.getNo())
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        menu.setName(menuDTO.getName());  // 메뉴 이름 수정
        menu.setUseYN(menuDTO.getUseYN()); // 필요하면 다른 필드도 수정
        menuRepository.save(menu); // 저장
    }

    // 메뉴 삭제 (useYN을 'N'으로 변경)
    public void deleteMenu(MenuDTO menuDTO) {
        // 메뉴 ID로 찾아서 삭제 처리
        Menu menu = menuRepository.findById(menuDTO.getNo())
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        menu.setUseYN('N'); // useYN을 'N'으로 변경
        menuRepository.save(menu); // 저장
    }

}
