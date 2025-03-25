package com.java.cafe.service;

import com.java.cafe.dto.BoardDTO;
import com.java.cafe.dto.MenuDTO;
import com.java.cafe.dto.PostDTO;
import com.java.cafe.entity.Board;
import com.java.cafe.entity.Menu;
import com.java.cafe.entity.Post;
import com.java.cafe.repository.BoardRepository;
import com.java.cafe.repository.MenuRepository;
import com.java.cafe.repository.PostRepository;
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
    private final PostRepository postRepository;

    @Override
    public Board save(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public Optional<Board> cafeBaseInfo(Integer type, String domain){
        return boardRepository.findByTypeAndDomain(type, domain);
    }

    // 카페 메인페이지 관련 정보
    @Override
    public BoardDTO cafeInfo(int type, String domain) {
        Board board = boardRepository.findByTypeAndDomain(type, domain)
                .orElseThrow(() -> new RuntimeException("도메인 정보를 찾을 수 없음"));
        BoardDTO boardDTO = new BoardDTO(
                board.getNo(),
                board.getRegUserNo(),
                board.getType(),
                board.getName(),
                board.getRegDate(),
                board.getDomain(),
                board.getDescription(),
                board.getUseYN()
        );
        return boardDTO;
    }

    // 메뉴 리스트 가져오기
    @Override
    public List<MenuDTO> getMenuList(String domain) {
        Optional<Board> boardSelect = boardRepository.findByTypeAndDomain(1, domain);
        if (boardSelect.isEmpty()) {
            throw new IllegalArgumentException("Board not found for domain: " + domain);
        }
        Board board = boardSelect.get();
        Integer boardNo = board.getNo();

        List<Menu> menus = menuRepository.findByBoardNoAndRefAndUseYNOrderByOrderNoAsc(boardNo, 0, 'Y');
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

    @Override
    public Menu getMenu(Integer no) {
        Menu menu = menuRepository.findById(no).orElseThrow();
        return menu;
    }

    // 메뉴 생성
    @Override
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
    @Override
    public MenuDTO editMenu(String domain, MenuDTO menuDTO) {
        Menu menu = menuRepository.findById(menuDTO.getNo())
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        menu.setName(menuDTO.getName());
        menu.setRef(menuDTO.getRef());
        menuRepository.save(menu);
        return menuDTO;
    }

    // 메뉴 삭제
    @Override
    public String deleteMenu(String domain, Integer no) {
        Menu menu = menuRepository.findById(no)
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        menu.setUseYN('N');
        menuRepository.save(menu);
        return "success";
    }

    @Override
    public List<Post> getPostList(Integer no) {
        return postRepository.findByMenuNoAndUseYN(no, 'Y');
    }

//    @Override
//    public PostDTO createPost(PostDTO postDTO) {
//        return null;
//    }

}