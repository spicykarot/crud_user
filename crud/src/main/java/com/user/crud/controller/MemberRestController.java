package com.user.crud.controller;

import com.user.crud.model.Member;
import com.user.crud.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberRestController {

    @Autowired
    private MemberService service;

    @PostMapping("/registerMember")
    @CrossOrigin(origins = "http://localhost:4200")
    public Member registerMember(@RequestBody Member Member) throws Exception {
        String tempEmailId = Member.getEmailId();
        if (tempEmailId != null && !"".equals(tempEmailId)) {

            Member Memberobj = service.fetchUserByEmailId(tempEmailId);
            if (Memberobj != null) {
                throw new Exception("Member with " + tempEmailId + " is already exist");
            }
        }
        Member MemberObj = null;
        MemberObj = service.saveUser(Member);
        return MemberObj;
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public Member loginMember(@RequestBody Member Member) throws Exception {
        String tempEmailId = Member.getEmailId();
        String tempPass = Member.getPassword();
        Member MemberObj = null;
        if (tempEmailId != null && tempPass != null) {
            MemberObj = service.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);
        }
        if (MemberObj == null) {
            throw new Exception("Bad credentials");
        }
        return MemberObj;
    }
}
