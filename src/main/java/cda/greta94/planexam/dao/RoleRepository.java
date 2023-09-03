package cda.greta94.planexam.dao;

import cda.greta94.planexam.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
