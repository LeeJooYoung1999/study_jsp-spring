package org.scoula.board.mapper;

import org.apache.ibatis.annotations.Select;
import org.scoula.board.domain.BoardVO;

import java.util.List;

public interface BoardMapper {
    //게시판의 목록을 얻는 getList() 메서드 -- select
    public List<BoardVO> getList();
    //게시판의 글하나를 얻는 get(Long no) 메서드
    public BoardVO get(Long no);
    //게시판의 글을 추가하는 int create(vo)메서드
    public void create(BoardVO board);  //원래는 int인데, 교재에선 그냥 void씀
    //게시판의 글을 수정하는 int update(vo) 메서드
    public int update(BoardVO board);
    //게시판의 글을 삭제하는 int delete(Long no)
    public int delete(Long no);
}
