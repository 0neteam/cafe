package com.java.cafe.service;

import com.java.cafe.dto.MenuDTO;
import com.java.cafe.dto.PostDTO;
import com.java.cafe.entity.Menu;
import com.java.cafe.entity.Post;

import java.util.List;

public interface CafeEachService {

    public List<MenuDTO> getMenuList(String domain);
    public MenuDTO createMenu(String domain, MenuDTO menuDTO);
    public MenuDTO editMenu(String domain, MenuDTO menuDTO);
    public String deleteMenu(String domain, MenuDTO menuDTO);

    public List<MenuDTO> getMenuPost(String domain);

//    public List<Post> getPostList(String domain, PostDTO postDTO);
//    public PostDTO createPost(PostDTO postDTO);

}
