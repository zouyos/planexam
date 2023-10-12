package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.RoleRepository;
import cda.greta94.planexam.dao.UtilisateurRepository;
import cda.greta94.planexam.dto.PasswordDto;
import cda.greta94.planexam.dto.UtilisateurDto;
import cda.greta94.planexam.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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

  public Utilisateur inscrireUser(UtilisateurDto dto) {
    Utilisateur user = this.convertToEntity(dto);
    user.setRole(roleRepository.findById(2l).get());
    user.setMdp(encoder.encode(user.getMdp()));
    //Pour récup un mdp hashé et créer un compte admin dans le data.sql (penser à changer l'id) :
    //System.out.println(user.getMdp());
    return utilisateurRepository.save(user);
  }

  public Utilisateur saveNewPassword(Utilisateur user){
    if (user.getId() != null) {
      user.setMdp(encoder.encode(user.getMdp()));
      return utilisateurRepository.save(user);
    }
    throw new NoSuchElementException();
  }

  public Utilisateur convertToEntity(PasswordDto dto){
    Utilisateur utilisateur;
    if(dto.getToken() == null){
      throw new NoSuchElementException();
    }
    else {
      utilisateur = utilisateurRepository.findByJetonResetMdps_TokenIgnoreCase(dto.getToken()).orElseThrow();
      utilisateur.setMdp(dto.getMdp1());
    }
    return utilisateur;
  }

  public Utilisateur findByEmail(String email){
    return this.utilisateurRepository.findByEmail(email);
  }
}
