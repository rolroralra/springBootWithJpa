package com.example.springbootwithjpa.repository;

import com.example.springbootwithjpa.domain.Member;
import com.example.springbootwithjpa.repository.common.JpaRepositoryTest;
import com.example.springbootwithjpa.repository.data.MemberDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

class MemberRepositoryTest extends JpaRepositoryTest<Member, Long> {
    @Autowired
    private MemberRepository repository;

    @Override
    protected JpaRepository<Member, Long> repository() {
        return repository;
    }

    @Override
    protected Member createTestInstance() {
        return MemberDataSet.testData("test");
    }
}
