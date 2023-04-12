package com.metanet.metamungmung.controller.member;

import com.metanet.metamungmung.dto.member.MemberDTO;
import com.metanet.metamungmung.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService service;

    @GetMapping("")
    public List<MemberDTO> list() {
        return service.getList();
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody MemberDTO member) {
        System.out.println(member);
        service.signUp(member);
        return ResponseEntity.ok().build();
    }
}
