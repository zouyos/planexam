package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.RoleRepository;
import cda.greta94.planexam.dao.UtilisateurRepository;
import cda.greta94.planexam.dto.UtilisateurDto;
import cda.greta94.planexam.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {
  private UtilisateurRepository utilisateurRepository;
  private RoleRepository roleRepository;
  private PasswordEncoder encoder;

  @Autowired
  public UtilisateurService(UtilisateurRepository utilisateurRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
    this.utilisateurRepository = utilisateurRepository;
    this.roleRepository= roleRepository;
    this.encoder=encoder;
  }

  public Utilisateur convertToEntity(UtilisateurDto dto){
    Utilisateur utilisateur;
    if (dto.getId() == null) {
      utilisateur = new Utilisateur();
    } else {
      utilisateur = utilisateurRepository.findById(dto.getId()).orElse(new Utilisateur());
    }
    utilisateur.setEmail(dto.getEmail());
    utilisateur.setMdp(dto.getMdp1NonEncode());
    utilisateur.setRole(dto.getRole());
    return utilisateur;
  }

  public Utilisateur inscrireClient(UtilisateurDto dto) {
    Utilisateur user = this.convertToEntity(dto);
    user.setRole(roleRepository.findById(2l).get());
    user.setMdp(encoder.encode(user.getMdp()));
    System.out.println(user.getMdp());
    return utilisateurRepository.save(user);
  }
}
