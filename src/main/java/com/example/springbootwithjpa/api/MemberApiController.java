package com.example.springbootwithjpa.api;

import com.example.springbootwithjpa.api.dto.CreateMemberRequest;
import com.example.springbootwithjpa.api.dto.CreateMemberResponse;
import com.example.springbootwithjpa.api.dto.MemberDto;
import com.example.springbootwithjpa.api.dto.UpdateMemberRequest;
import com.example.springbootwithjpa.api.dto.UpdateMemberResponse;
import com.example.springbootwithjpa.domain.Member;
import com.example.springbootwithjpa.service.MemberService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @GetMapping("/api/v1/members")
    public List<Member> membersV1(Pageable pageable) {
        return memberService.findAll(pageable).getContent(); // Member.orders 에 @JsonIgnore 필요함
    }

    @GetMapping("/api/v2/members")
    public ApiResult<Page<MemberDto>> membersV2(Pageable pageable) {
        return ApiResult.success(
            memberService.findAll(pageable)
                .map(MemberDto::of)
        );
    }

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.insertMember(member);

        return new CreateMemberResponse(id);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = request.createMember();

        Long id = memberService.insertMember(member);

        return new CreateMemberResponse(id);
    }

    @PatchMapping("api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable Long id, @RequestBody @Valid UpdateMemberRequest request) {
        memberService.updateMember(id, request.getName());

        Member findMember = memberService.findById(id);

        return UpdateMemberResponse.of(findMember);
    }
}
