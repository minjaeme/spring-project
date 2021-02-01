package me.inners.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import me.inners.domain.BoardVO;
import me.inners.domain.Criteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

    @Setter(onMethod_ = @Autowired)
    private BoardMapper mapper;

    @Test
    public void testGetList() {
        mapper.getList().forEach(board -> log.info(board));
    }

    @Test
    public void testPaging() {
        Criteria cri = new Criteria();
        // 10개씩 3페이지
        cri.setAmount(3);
        cri.setPageNum(10);
        List<BoardVO> list = mapper.getListWithPaging(cri);

        list.forEach(boardVO-> log.info(boardVO.getBno()));
    }

    @Test
    public void testInsert() {
        BoardVO boardVO = new BoardVO();
        boardVO.setTitle("새로 작성하는 글");
        boardVO.setContent("새로 작성하는 내용");
        boardVO.setWriter("newbie");

        mapper.insert(boardVO);

        log.info(boardVO);
    }

    @Test
    public void testInsertSelectKey() {
        BoardVO boardVO = new BoardVO();
        boardVO.setTitle("새로 작성하는 글 select key");
        boardVO.setContent("새로 작성하는 내용 select key");
        boardVO.setWriter("newbie");

        mapper.insertSelectKey(boardVO);

        log.info(boardVO);
    }

    @Test
    public void testRead() {

        BoardVO boardVO = mapper.read(5L);

        log.info(boardVO);
    }

    @Test
    public void testDelete() {
        log.info("DELETE COUNT: " + mapper.delete(3L));
    }

    @Test
    public void testUpdate() {

        BoardVO boardVO = new BoardVO();

        boardVO.setBno(5L);
        boardVO.setTitle("수정된 제목");
        boardVO.setContent("수정된 내용");
        boardVO.setWriter("user00");

        int count = mapper.update(boardVO);
        log.info("UPDATE COUNT: " + count);
    }
}
