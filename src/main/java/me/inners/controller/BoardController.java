package me.inners.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import me.inners.domain.BoardVO;
import me.inners.domain.Criteria;
import me.inners.domain.PageDTO;
import me.inners.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

    private BoardService service;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void list(Criteria cri, Model model) {
        log.info("list: " + cri);
        model.addAttribute("list", service.getList(cri));
        model.addAttribute("pageMaker", new PageDTO(cri, 123));
    }
//    public void list(Model model) {
//        log.info("list");
//        model.addAttribute("list", service.getList());
//    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(BoardVO board, RedirectAttributes rttr) {
        log.info("register: " + board);

        service.register(board);

        rttr.addFlashAttribute("result", board.getBno());

        return "redirect:/board/list";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register() {

    }

    @RequestMapping(value = {"/get", "/modify"}, method = RequestMethod.GET)
    public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {

        log.info("/get or modify");
        model.addAttribute("board", service.get(bno));
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
        log.info("modify: " + board);

        if (service.modify(board)) {
            rttr.addFlashAttribute("result", "success");
        }

        rttr.addFlashAttribute("pageNum", cri.getPageNum());
        rttr.addFlashAttribute("amount", cri.getAmount());

        return "redirect:/board/list";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
        log.info("remove..." + bno);

        if(service.remove(bno)) {
            rttr.addFlashAttribute("result", "success");
        }

        rttr.addFlashAttribute("pageNum", cri.getPageNum());
        rttr.addFlashAttribute("amount", cri.getAmount());

        return "redirect:/board/list";
    }

    // TEST
    @RequestMapping(value = "/example", method = RequestMethod.GET)
    public String printExample() {
        return "exam";
    }

}
