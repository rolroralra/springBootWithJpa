package com.example.springbootwithjpa.controller.param;

import com.example.springbootwithjpa.domain.Address;
import com.example.springbootwithjpa.domain.Member;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberForm {
    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;

    private String city;

    private String street;

    private String zipcode;

    public Address convertToAddress() {
        return new Address(city, street, zipcode);
    }

    public Member createMember() {
        Member member = new Member();
        member.setName(name);
        member.setAddress(convertToAddress());

        return member;
    }
}
