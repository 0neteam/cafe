package com.java.cafe.service;

import com.java.cafe.config.Utils;
import com.java.cafe.dto.BoardDTO;
import com.java.cafe.dto.MenuDTO;
import com.java.cafe.dto.PostDTO;
import com.java.cafe.dto.PostResDTO;
import com.java.cafe.entity.Board;
import com.java.cafe.entity.Menu;
import com.java.cafe.entity.Post;
import com.java.cafe.entity.User;
import com.java.cafe.repository.BoardRepository;
import com.java.cafe.repository.MenuRepository;
import com.java.cafe.repository.PostRepository;
import com.java.cafe.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CafeEachServiceImp implements CafeEachService {

    private final MenuRepository menuRepository;
    private final BoardRepository boardRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final Utils utils ;

    // 카페 기본정보 수정하기
    @Override
    public Board save(Board board) {
        return boardRepository.save(board);
    }

    // 카페 기본정보 수정페이지에 기본 정보 보이기
    @Override
    public Optional<Board> cafeBaseInfo(Integer type, String domain){
        return boardRepository.findByTypeAndDomain(type, domain);
    }

    @Override
    public String infoEdit(String domain, BoardDTO boardDTO){
        try {
            Board board = boardRepository.findByTypeAndDomain(1, domain)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다: domain=" + domain));
            board.setName(boardDTO.getName());
            board.setDomain(boardDTO.getDomain());
            board.setDescription(boardDTO.getDescription());
            boardRepository.save(board);
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }

    // 카페 메인페이지 관련 정보
    @Override
    public String cafeInfo(String domain, Model model) {
        int type = 1;
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
        String checkDomain = boardDTO.getDomain();
        model.addAttribute("boardDTO", boardDTO);

        if (domain.equals(checkDomain)) {
            return "success";
        }
        return "fail";
    }

    // 메뉴 리스트 가져오기
    @Override
    public List<MenuDTO> getMenuList(String domain, Model model) {
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
        model.addAttribute("menuList", filterMenus);
        model.addAttribute("domain", domain);
        return filterMenus;
    }

    @Override
    public Menu getMenu(Integer no, Model model) {
        Menu menu = menuRepository.findById(no).orElseThrow();
        model.addAttribute("menu", menu);
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

        Integer orderNo = (menuRepository.countByBoardNoAndRef(board.getNo(), menuDTO.getRef())) + 1;

        Menu menu = Menu.builder()
                .board(board)
                .orderNo(orderNo)
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
        Optional<Board> boardSelect = boardRepository.findByTypeAndDomain(1, domain);
        if (boardSelect.isEmpty()) {
            throw new IllegalArgumentException("Board not found for domain: " + domain);
        }
        Board board = boardSelect.get();
        Menu menu = menuRepository.findById(menuDTO.getNo())
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        Integer orderNo = (menuRepository.countByBoardNoAndRef(board.getNo(), menuDTO.getRef())) + 1;
        menu.setName(menuDTO.getName());
        menu.setOrderNo(orderNo);
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
    public List<Menu> getChildList(Integer no) {
        return menuRepository.findByRefAndUseYN(no, 'Y');
    }

    @Override
    public List<Post> getPostList(Integer no, Model model) {
        List<Post> postList = postRepository.findByMenuNoAndUseYN(no, 'Y');
        model.addAttribute("postList", postList);
        return postRepository.findByMenuNoAndUseYN(no, 'Y');
    }

    @Override
    public PostResDTO writeCreate(PostDTO postDTO, HttpServletRequest req) {
        String status = "fail";
        int loginUser = Integer.parseInt(utils.getUserNo(req));
        int no = 0;
        try {
            Menu menu = menuRepository.findById(postDTO.getMenuNo()).orElseThrow();
            User user = userRepository.findById(loginUser).orElseThrow();
            Post post = Post.builder()
                    .menu(menu)
                    .title(postDTO.getTitle())
                    .content(postDTO.getContent())

                    // 추가 받아와야 하는 데이터
                    .regUserNo(loginUser)
                    .viewCount(0)
                    .useYN('Y')
                    .user(user)

                    .build();
            post = postRepository.save(post);
            if(post.getNo() > 0) {
                status = "success";
                no = post.getNo();
            }
        } catch (Exception e) {}
        //return "fail";
        return PostResDTO.builder()
                .status(status)
                .no(no)
                .build();
    }

    @Override
    public void read(String domain, Integer no, Model model, HttpServletRequest req) {
        int loginUser = Integer.parseInt(utils.getUserNo(req));
        Post post = postRepository.findById(no).orElseThrow();
        PostDTO postDTO = PostDTO.builder().build();
        if(post != null) {
            postDTO = PostDTO.builder()
                    .no(post.getNo())
                    .menuNo(post.getMenu().getNo())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .regName(post.getUser().getName())
                    .menuName(post.getMenu().getName())
                    .regDate(post.getRegDate())
                    .viewCount(post.getViewCount())
                    .regUserNo(post.getRegUserNo())
                    .build();
        }
        Integer menuNo = postDTO.getMenuNo();
        List<Post> postList = postRepository.findAllByMenuNoAndUseYNOrderByRegDate(menuNo, 'Y');
        List<Integer> postNoList = postList.stream()
                .map(Post::getNo)
                .collect(Collectors.toList());
        int nextIndex = 0;
        int beforeIndex = 0;
        int x = 0;
        if(postNoList.size()>1){
            for(x=0; x<postNoList.size(); x++){
                if (postNoList.get(x) == postDTO.getNo()){
                    if (x == 0) {
                        nextIndex = postNoList.get(x+1);
                        break;
                    } else if (x == postNoList.size()-1) {
                        beforeIndex = postNoList.get(x-1);
                        break;
                    } else {
                        beforeIndex = postNoList.get(x-1); //이전 번호
                        nextIndex = postNoList.get(x+1); //다음 번호
                        break;
                    }
                }
            }
        }
        model.addAttribute("post", postDTO);
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("nextIndex", nextIndex);
        model.addAttribute("beforeIndex", beforeIndex);
        model.addAttribute("domain", domain);

    }

    @Override
    public PostResDTO writeEdit(Integer no, PostDTO postDTO, HttpServletRequest req) {
        postDTO.setNo(no);
        String loginUser = utils.getUserNo(req);
        int modUserNo = Integer.parseInt(loginUser);
        log.info("No : {}, POST : {}", no, postDTO);
        String status = "fail";

        try {
            // Menu menu = menuRepository.findById(postDTO.getMenuNo()).orElseThrow();
            // User user = userRepository.findById(1).orElseThrow();
            Post post = postRepository.findById(no).orElseThrow();
            post.setTitle(postDTO.getTitle());
            post.setContent(postDTO.getContent());
            post.setModDate(LocalDateTime.now());
            post.setModUserNo(modUserNo);
            post = postRepository.save(post);
            if(post.getNo() > 0) {
                status = "success";
            }
        } catch (Exception e) {}
        return PostResDTO.builder()
                .status(status)
                .no(no)
                .build();
    }

    @Override
    public PostResDTO writeDel(Integer no) {
        String status = "fail";
        try {
            Post post = postRepository.findById(no).orElseThrow();
            post.setUseYN('N');
            post = postRepository.save(post);
            if(post.getNo() > 0) {
                status = "success";
            }
        } catch (Exception e) {}
        return PostResDTO.builder()
                .status(status)
                .no(no)
                .build();
    }

}