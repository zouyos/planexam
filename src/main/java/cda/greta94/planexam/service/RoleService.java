package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.RoleRepository;
import cda.greta94.planexam.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
  private RoleRepository roleRepository;
  @Autowired
  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  public Role save(Role role){
    return this.roleRepository.save(role);
  }
}
