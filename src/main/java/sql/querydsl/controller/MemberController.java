package sql.querydsl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sql.querydsl.domain.Member;
import sql.querydsl.dto.MemberDto;
import sql.querydsl.repository.MemberRepository;

import java.util.List;

@RestController
public class MemberController {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/")
    public void saveMember() {
        Member member1 = new Member("choi", "27");
        Member member2 = new Member("han", "15");
        Member member3 = new Member("kim", "29");
        Member member4 = new Member("jin", "30");
        Member member5 = new Member("hong", "22");

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
        memberRepository.save(member5);

        System.out.println("세이브완료");

    }

    @GetMapping("/getMemberList")
    public List<MemberDto> getMemberList(@RequestParam String param) {
        System.out.println("param : " + param);
        List<MemberDto> memberDtoList = memberRepository.findMemberList(param);

        return  memberDtoList;
    }
}
