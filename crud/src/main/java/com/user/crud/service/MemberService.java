package com.user.crud.service;

import com.user.crud.model.Member;
import com.user.crud.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository repo;

    public Member saveUser(Member user) {
        return repo.save(user);
    }

    public Member fetchUserByEmailId(String email) {
        return repo.findByEmailId(email);
    }

    public Member fetchUserByEmailIdAndPassword(String email, String password) {
        return repo.findByEmailIdAndPassword(email, password);
    }
}
