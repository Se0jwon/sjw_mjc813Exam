package com.mjc813.webcrud.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/member/")
    public String member() {
        return "member/index";
    }

    @GetMapping("/member/add")
    public String memberAdd() {
        return "member/add";
    }

    @PostMapping("/member/insert")
    public String memberInsert(@ModelAttribute MemberDto obj) {
        this.memberRepository.addMember(obj);
        return "redirect:./list";
    }

    @GetMapping("/member/list")
    public String list(Model model) {
        List<MemberDto> arraylist = this.memberRepository.findAll();
        model.addAttribute("list", arraylist);
        return "/member/members";
    }

    @GetMapping("/member/view")
    public String view(Model model, @RequestParam("id") Long num) {
        MemberDto item = this.memberRepository.findById(num);
        model.addAttribute("one", item);
        return "/member/view";
    }

    @GetMapping("/member/modify")
    public String modify(Model model, @RequestParam("id") Long num) {
        MemberDto item = this.memberRepository.findById(num);
        model.addAttribute("one", item);
        return "/member/modify";
    }

    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDto obj) {
        this.memberRepository.update(obj);
        return "redirect:./list";
    }

    @PostMapping("/member/delete")
    public String delete(@ModelAttribute MemberDto obj) {
        this.memberRepository.deleteById(obj.getId());
        return "redirect:./list";
    }
}
