package com.example.springbootwithjpa.repository.data;

import com.example.springbootwithjpa.domain.Member;

public class MemberDataSet {
    public static Member testData(String name) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(AddressDataSet.testData());

        return member;
    }
}
