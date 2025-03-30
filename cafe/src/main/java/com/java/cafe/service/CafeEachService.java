package com.java.cafe.service;

import com.java.cafe.dto.BoardDTO;
import com.java.cafe.dto.MenuDTO;
import com.java.cafe.dto.PostDTO;
import com.java.cafe.dto.PostResDTO;
import com.java.cafe.entity.Board;
import com.java.cafe.entity.Menu;
import com.java.cafe.entity.Post;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import java.util.List;

public interface CafeEachService {

    public Board save(Board board);
    public String cafeBaseInfo(String domain);
    public String infoEdit(String domain, BoardDTO boardDTO);
    public String cafeInfo(String domain, Model model); //카페 가입정보

    public List<MenuDTO> getMenuList(String domain, Model model);
    public Menu getMenu(Integer no, Model model);
    public String createMenu(String domain, MenuDTO menuDTO);
    public String editMenu(String domain, MenuDTO menuDTO);
    public String deleteMenu(String domain, Integer no);
    public List<Menu> getChildList(Integer no);

    public List<Post> getPostList(Integer no, Model model);
    public PostResDTO writeCreate(PostDTO postDTO, HttpServletRequest req);
    public void read(String domain, Integer no, Model model, HttpServletRequest req);
    public PostResDTO writeEdit(Integer no, PostDTO postDTO, HttpServletRequest req);
    public PostResDTO writeDel(Integer no);
}