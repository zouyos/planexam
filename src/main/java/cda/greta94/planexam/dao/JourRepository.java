package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.Jour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JourRepository extends JpaRepository<Jour, Long> {
}
