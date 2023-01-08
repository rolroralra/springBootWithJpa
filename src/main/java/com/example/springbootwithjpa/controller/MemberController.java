package com.example.springbootwithjpa.controller;

import com.example.springbootwithjpa.controller.param.MemberForm;
import com.example.springbootwithjpa.domain.Member;
import com.example.springbootwithjpa.service.MemberService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public String memberList(Pageable pageable, Model model) {
        List<Member> members = memberService.findAll(pageable).getContent();

        model.addAttribute("members", members);

        return "members/memberList";
    }

    @GetMapping(value = "/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping(value = "/new")
    public String createMember(@Valid MemberForm memberForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/createMemberForm";
        }

        Member member = memberForm.createMember();

        memberService.insertMember(member);

        return "redirect:/";
    }
}
