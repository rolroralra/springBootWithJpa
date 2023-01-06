package com.example.springbootwithjpa.service;

import com.example.springbootwithjpa.domain.Member;
import com.example.springbootwithjpa.repository.MemberRepository;
import com.google.common.base.Preconditions;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long saveMember(Member member) {
        validateDuplicateMember(member);

        memberRepository.save(member);

        return member.getId();
    }

    public Page<Member> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    public Optional<Member> findById(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        Preconditions.checkArgument(member != null);
        Preconditions.checkArgument(StringUtils.isNotBlank(member.getName()));

        if (memberRepository.findByName(member.getName()).isPresent()) {
            throw new IllegalArgumentException("Member.name is duplicated");
        }
    }
}
