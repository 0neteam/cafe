package com.java.cafe.repository;

import com.java.cafe.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    public List<Menu> findByBoardNoAndRefAndUseYN(Integer boardNo, Integer ref, char useYN);


}
