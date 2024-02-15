package com.example.oauth2.repository;


import com.example.oauth2.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Member, String> {
    Optional<Member> findBySocialId(String socialId);
}
