package com.metanet.metamungmung.controller.member;

import com.metanet.metamungmung.dto.member.MemberDTO;
import com.metanet.metamungmung.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService service;

    @GetMapping("")
    public List<MemberDTO> getMemberList() {
        return service.getMemberList();
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody MemberDTO member) {
        System.out.println(member);
        service.signUp(member);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/idCheck")
    public int idCheck(@RequestBody MemberDTO member) {
        String memberId = member.getMemberId();
        int result = service.idCheck(memberId);
        if(result == 0)
        {
            System.out.println("사용 가능한 아이디");
        }
        else
        {
            System.out.println("이미 존재하는 아이디");
        }
        return result;
    }

    @GetMapping("/login")
    public void login() {

    }

    @PostMapping("/login")
    public ResponseEntity<MemberDTO> login(@RequestBody MemberDTO member, HttpSession session) {
        System.out.println(member);
        MemberDTO loginMember = service.login(member);
        System.out.println(loginMember);

        if (loginMember == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginMember);
        }

        session.setAttribute("memberId", loginMember.getMemberId());
        session.setAttribute("authority", loginMember.getAuthority());

        return ResponseEntity.status(HttpStatus.OK).body(loginMember);
    }

    @GetMapping("/modify")
    public void modify() {}

    @PatchMapping("/modify/{memberIdx}")
    public void modify(@PathVariable("memberIdx") Long memberIdx, @RequestBody MemberDTO member) {
        System.out.println("Ddddddddddddddddd");
        member.setMemberIdx(memberIdx);
        service.modify(member);
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
