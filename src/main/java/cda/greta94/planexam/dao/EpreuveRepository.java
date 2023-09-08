package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.Epreuve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpreuveRepository extends JpaRepository<Epreuve, Long> {
}
