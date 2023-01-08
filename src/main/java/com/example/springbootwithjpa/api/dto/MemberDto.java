package com.example.springbootwithjpa.api.dto;

import com.example.springbootwithjpa.domain.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class MemberDto {
    private final String name;

    public static MemberDto of(Member member) {
        return new MemberDto(member.getName());
    }
}
