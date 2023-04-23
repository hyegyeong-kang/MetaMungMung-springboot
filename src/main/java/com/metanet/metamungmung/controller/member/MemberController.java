package com.metanet.metamungmung.controller.member;

import com.metanet.metamungmung.dto.member.MemberDTO;
import com.metanet.metamungmung.dto.member.PetDTO;
import com.metanet.metamungmung.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/findId")
    public String findId(@RequestBody MemberDTO member) {
        String email = member.getEmail();
        System.out.println(service.findId(email));
        return service.findId(email);
    }

    @GetMapping("/my")
    public MemberDTO getMemberInfo() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long memberIdx = 0L;

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            MemberDTO memberDTO = (MemberDTO) userDetails;
            memberIdx = memberDTO.getMemberIdx();

            System.out.println("memberIdx 나와주세요~~~~~~~~~~~~~~~~~"+ memberIdx);
        }

        return service.getMemberInfo(memberIdx);
    }

    @PostMapping("/login")
    public ResponseEntity login(@AuthenticationPrincipal MemberDTO member) {
        System.out.println(member);
        return null;
    }


    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody MemberDTO member) {
        System.out.println("member" + member);
        service.signUp(member);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/idCheck")
    public int idCheck(@RequestBody MemberDTO member) {
        String memberId = member.getMemberId();
        int result = service.idCheck(memberId);
        if (result == 0) {
            System.out.println("사용 가능한 아이디");
        } else {
            System.out.println("이미 존재하는 아이디");
        }
        return result;
    }

    @GetMapping("/modify")
    public void modify() {
    }

    @PatchMapping("/modify/{memberIdx}")
    public void modify(@AuthenticationPrincipal MemberDTO memberDTO, @PathVariable("memberIdx") Long memberIdx, @RequestBody MemberDTO member) {
        System.out.println("MemberController modify");
        System.out.println(memberDTO);
        member.setMemberIdx(memberIdx);
        service.modify(member);
    }

    @PatchMapping("/withdrawal/{memberIdx}")
    public void withdrawal(@PathVariable("memberIdx") Long memberIdx, @RequestBody MemberDTO member) {
        member.setMemberIdx(memberIdx);
        service.withdrawal(member);
    }


    //반려동물
    @GetMapping("/pets")
    public List<PetDTO> getPetList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Long memberIdx = 0L;

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            MemberDTO memberDTO = (MemberDTO) userDetails;
            memberIdx = memberDTO.getMemberIdx();

            System.out.println("memberIdx 나와주세요~~~~~~~~~~~~~~~~~"+ memberIdx);
        }

        return service.getPetList(memberIdx);
    }

    @PostMapping("/pets/register")
    public ResponseEntity<Void> register(@RequestBody PetDTO pet) {
        System.out.println(pet);
        service.register(pet);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/pets/{petIdx}")
    public void delete(@PathVariable("petIdx") Long petIdx) {
        PetDTO pet = new PetDTO();
        System.out.println(petIdx + "--------------------");
        pet.setPetIdx(petIdx);
        service.delete(pet);
    }

}
