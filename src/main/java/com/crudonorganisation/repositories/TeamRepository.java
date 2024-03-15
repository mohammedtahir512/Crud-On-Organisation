package com.crudonorganisation.repositories;
import com.crudonorganisation.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}