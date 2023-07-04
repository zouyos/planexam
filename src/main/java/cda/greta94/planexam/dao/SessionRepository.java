package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
