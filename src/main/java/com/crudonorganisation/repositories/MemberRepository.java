package com.crudonorganisation.repositories;
import com.crudonorganisation.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}