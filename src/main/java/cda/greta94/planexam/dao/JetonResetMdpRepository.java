package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.JetonResetMdp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JetonResetMdpRepository extends JpaRepository<JetonResetMdp,Long> {
    JetonResetMdp findByToken(String token);
}
