package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    
    //private final MemberRepository memberRepo = new MemoryMemberRepository();//기존의 선언 방식
    private final MemberRepository memberRepo;
    public MemberService(MemberRepository memberRepo) {
        this.memberRepo = memberRepo;
    }

    /**
     * 회원가입
     * @param member
     * @return id
     */
    public Long join(Member member) {

//        Optional<Member> rst = memberRepo.findByName(member.getName());
//
//        rst.ifPresent(m -> {
//            throw new IllegalStateException("member name already exist.");
//        });
        validateDuplicateMember(member);//중복 회원 인증

        memberRepo.save(member);
        return member.getId();
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepo.findAll();
    }

    /**
     * 회원 조회
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepo.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        memberRepo.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 이름입니다.");
                        });
    }
}
