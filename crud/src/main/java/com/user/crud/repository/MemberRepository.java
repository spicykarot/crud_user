package com.user.crud.repository;

import com.user.crud.model.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    public Member findByEmailId(String emailId);

    public Member findByEmailIdAndPassword(String emailId, String password);
}
