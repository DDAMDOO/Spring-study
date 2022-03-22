package com.example.demo.repository;
import com.example.demo.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    //테스트는 서로 순서와 상관없이, 의존관계 없이 설계가 되어야 하기 때문에 테스트가 끝날 때 마다 저장소나 공용데이터를 초기화 해 주어야 함.
    //TDD 간략 설명 - 테스트 코드를 먼저 만들고 나서 구현물을 만드는 것.

    MemoryMemberRepository repo = new MemoryMemberRepository();

    @AfterEach // 각각의 모듈 실행 이후에 동작
    public void afterEach() {
        repo.clear();
    }
    // 각각의 테스트 이후 저장된 객체들을 삭제해주기 위함.

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repo.save(member);

        Member rst = repo.findById(member.getId()).get();

        assertThat(member).isEqualTo(rst);
        //org.assertj.core.api.Assertions 로 import

        //Assertions.assertEquals(member, rst);
        //org.junit.jupiter.api.Assertions 로 import
    }

    @Test
    public void findByName(){
        Member mem1 = new Member();
        Member mem2 = new Member();

        mem1.setName("1");
        mem2.setName("2");

        repo.save(mem1);
        repo.save(mem2);

        Member rst = repo.findByName("1").get();

        assertThat(rst).isEqualTo(mem1);
    }

    @Test
    public void findAll() {
        Member mem1 = new Member();
        Member mem2 = new Member();

        mem1.setName("1");
        mem2.setName("2");

        repo.save(mem1);
        repo.save(mem2);

        List<Member> rst = repo.findAll();

        assertThat(rst.size()).isEqualTo(2);
    }

    public void clear(){

    }
}
