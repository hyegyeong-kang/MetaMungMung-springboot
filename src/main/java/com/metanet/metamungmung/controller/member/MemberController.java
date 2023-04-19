package com.metanet.metamungmung.controller.member;

import com.metanet.metamungmung.dto.member.MemberDTO;
import com.metanet.metamungmung.dto.member.PetDTO;
import com.metanet.metamungmung.service.member.MemberService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @GetMapping("/modify")
    public void modify() {}

    @PatchMapping("/modify/{memberIdx}")
    public void modify(@PathVariable("memberIdx") Long memberIdx, @RequestBody MemberDTO member) {
        member.setMemberIdx(memberIdx);
        service.modify(member);
    }

    @GetMapping("/pets")
    public List<PetDTO> getPetList() {
        return service.getPetList();
    }

    @PostMapping("/pets/register")
    public ResponseEntity<Void> register(@RequestBody PetDTO pet) {
        System.out.println(pet);
        service.register(pet);
        return ResponseEntity.ok().build();
    }

}
