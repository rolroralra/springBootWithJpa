package com.example.springbootwithjpa.repository;

import com.example.springbootwithjpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
