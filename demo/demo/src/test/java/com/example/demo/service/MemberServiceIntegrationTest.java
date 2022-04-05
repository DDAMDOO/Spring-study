package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //스프링 컨테이너를 띄워서 테스트 하는 것.
@Transactional //transactional 은 테스트 진행이 다 끝난 이후에 db에 넣었던 데이터를 다시 롤백해줌
class MemberServiceIntegrationTest {

//    MemberService memberService = new MemberService();
//    MemoryMemberRepository memoryRepo = new MemoryMemberRepository();
    //위와 같이 두면 MemberService에 있는 MemoryMemberRepository와 다른 Repository가 될 수 있음
    @Autowired MemberService memberService;
    @Autowired MemberRepository memoryRepo;

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
}