package com.example.springbootwithjpa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.example.springbootwithjpa.domain.Member;
import com.example.springbootwithjpa.repository.MemberRepository;
import com.example.springbootwithjpa.repository.data.MemberDataSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("회원가입이 정상적으로 작동한다.")
    @Test
    void 회원가입() {
        // Given
        String givenMemberName = "rolroralra";
        Member member = MemberDataSet.testData(givenMemberName);

        // When
        Long savedId = memberService.insertMember(member);

        // Then
        assertThat(memberRepository.findById(savedId)).isPresent()
            .get()
            .hasFieldOrPropertyWithValue("name", givenMemberName);
    }

    @DisplayName("중복된 이름의 회원 가입할 경우, IllegalArgumentException 발생")
    @Test
    void 중복_이름_회원_가입() {
        // Given
        String duplicatedName = "rolroralra";
        Member member = MemberDataSet.testData(duplicatedName);
        Member duplicatedMember = MemberDataSet.testData(duplicatedName);

        Long savedId = memberService.insertMember(member);
        assertThat(memberRepository.findById(savedId)).isPresent();

        // Expected
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
            memberService.insertMember(duplicatedMember)
        );
    }
}
