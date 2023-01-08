package com.example.springbootwithjpa.api.dto;

import com.example.springbootwithjpa.domain.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class CreateMemberRequest {
    @NotBlank
    private String name;

    public Member createMember() {
        Member member = new Member();
        member.setName(name);

        return member;
    }
}
