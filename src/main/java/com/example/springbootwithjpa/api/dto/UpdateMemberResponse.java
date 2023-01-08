package com.example.springbootwithjpa.api.dto;

import com.example.springbootwithjpa.domain.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class UpdateMemberResponse {
    private final Long id;

    private final String name;

    public static UpdateMemberResponse of(Member member) {
        return new UpdateMemberResponse(member.getId(), member.getName());
    }
}
