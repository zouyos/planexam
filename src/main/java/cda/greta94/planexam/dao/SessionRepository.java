package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.SessionE5;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<SessionE5, Long> {
}
