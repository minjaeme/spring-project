package me.inners.service;

import me.inners.domain.BoardVO;
import me.inners.domain.Criteria;

import java.util.List;

public interface BoardService {

    public void register(BoardVO board);
    public BoardVO get(Long bno);
    public boolean modify(BoardVO board);
    public boolean remove(Long bno);
    // public List<BoardVO> getList();
    public List<BoardVO> getList(Criteria cri);
}
