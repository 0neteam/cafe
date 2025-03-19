package com.java.cafe.repository;

import com.java.cafe.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{
    public List<Board> findByType(int type);
    public List<Board> findByNameContainingIgnoreCase(String keyward);
}
