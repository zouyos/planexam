package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.Jury;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JuryRepository extends JpaRepository<Jury, Long> {
}
