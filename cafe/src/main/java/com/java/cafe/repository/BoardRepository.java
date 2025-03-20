package com.java.cafe.repository;

import com.java.cafe.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{
    public List<Board> findByType(int type);
    public List<Board> findByNameContainingIgnoreCase(String keyward);
    public Optional<Board> findByTypeAndDomain(Integer type, String domain);

    Integer findByDomain(String domain);
}
