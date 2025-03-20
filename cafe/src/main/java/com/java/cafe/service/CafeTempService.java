package com.java.cafe.service;

import com.java.cafe.dto.BoardDTO;
import com.java.cafe.entity.Board;
import java.util.Optional;

public interface CafeTempService {

    public BoardDTO cafeInfo(int type, String domain);

}
