package com.example.exam01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.exam01.dao.MemberDAO;
import com.example.exam01.dto.Member;

@Controller
public class MyController {
    
    @Autowired
    private MemberDAO mdao;

    // 홈페이지 인덱스 페이지
    @RequestMapping("/")
    public String doHome() {
        return "home";
    }

    // 로그인 페이지
    @RequestMapping("/login")
    public String doLogin() {
        return "login";
    }

    // 회원가입 페이지
    @RequestMapping("/signin")
    public String doSignin() {
        return "signin";
    }

    // "user"라는 이름으로 새로운 'Member' 객체를 생성하여 모델에 추가
    @GetMapping("/viewUser")
    public String doViewUser(Model model) {
        model.addAttribute("users", new Member());
        return "viewUser";
    }

    // 'member' 객체를 MongoDB 데이터베이스에 저장
    @PostMapping("/viewUser")
    public String newUser(Member member) {
        mdao.save(member);
        return "redirect:/login";
    }

    // 로그인시 등록되어있는 사용자인지 확인
    @RequestMapping("/loginOk")
    public String doLoginOk(Member member) {
        Member m = mdao.findByUserid(member.getUserid());
        if (m != null && m.getPassword().equals(member.getPassword())) {
            return "redirect:/";
        }
        else {
            return "login";
        }
    }

    // 아이디 중복확인하는 코드
    @PostMapping("/checkId")
    @ResponseBody
    public String doCheckId(@RequestParam String userid) {
        boolean isDup = mdao.checkId(userid);
        return Boolean.toString(isDup);
    }

    // 닉네임 중복확인하는 코드
    @PostMapping("/checkNick")
    @ResponseBody
    public String doCheckNick(@RequestParam String nickname) {
        boolean isDup = mdao.checkNick(nickname);
        return Boolean.toString(isDup);
    }

    // 이메일 중복확인하는 코드
    @PostMapping("/checkEmail")
    @ResponseBody
    public String doCheckEmail(@RequestParam String email) {
        boolean isDup = mdao.checkEmail(email);
        return Boolean.toString(isDup);
    }
}
