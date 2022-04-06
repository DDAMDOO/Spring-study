package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    
    //JPQL select m from Member m where m.name = ? 로 쿼리를 작성해줌
    @Override
    Optional<Member> findByName(String name);
}
