package com.java.cafe.service;

import com.java.cafe.dto.BoardDTO;
import com.java.cafe.dto.MenuDTO;
import com.java.cafe.dto.PostDTO;
import com.java.cafe.entity.Board;
import com.java.cafe.entity.Menu;
import com.java.cafe.entity.Post;

import java.util.List;
import java.util.Optional;

public interface CafeEachService {

    public Board save(Board board);
    public Optional<Board> cafeBaseInfo(Integer type, String domain);

    public BoardDTO cafeInfo(int type, String domain); //카페 가입정보

    public List<MenuDTO> getMenuList(String domain);
    public MenuDTO createMenu(String domain, MenuDTO menuDTO);
    public MenuDTO editMenu(String domain, MenuDTO menuDTO);
    public String deleteMenu(String domain, Integer no);

    public List<MenuDTO> getMenuPost(String domain);

//    public List<Post> getPostList(String domain, PostDTO postDTO);
//    public PostDTO createPost(PostDTO postDTO);

}
