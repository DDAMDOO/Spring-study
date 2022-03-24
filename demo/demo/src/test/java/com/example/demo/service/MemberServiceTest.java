package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memoryRepo = new MemoryMemberRepository();
    //위와 같이 두면 MemberService에 있는 MemoryMemberRepository와 다른 Repository가 될 수 있음
    MemberService memberService;
    MemoryMemberRepository memoryRepo;

    @BeforeEach
    public void beforeEach() {
        memoryRepo = new MemoryMemberRepository();
        memberService = new MemberService(memoryRepo);
    }

    @AfterEach
    public void afterEach() {
        memoryRepo.clear();
    }

    @Test
    void join() {
        //given
        Member mem = new Member();
        mem.setName("11");

        //when
        Long saveId = memberService.join(mem);

        //then
        Member findMem = memberService.findOne(saveId).get();
        Assertions.assertThat(mem.getName()).isEqualTo(findMem.getName());
    }

    @Test
    public void duplicateJoin() {
        //given
        Member mem1 = new Member();
        Member mem2 = new Member();

        mem1.setName("1");
        mem2.setName("1");

        //when
        memberService.join(mem1);

        //try ~ catch 문법을 assertThrows로 검증이 가능하다.
//        assertThrows(IllegalStateException.class, () -> memberService.join(mem2));
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(mem2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");

//        try {
//            memberService.join(mem2);
//            fail();
//        } catch(IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
//        }

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}