package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.SpecialiteRepository;
import cda.greta94.planexam.model.Role;
import cda.greta94.planexam.model.Specialite;
import cda.greta94.planexam.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

  private UtilisateurService utilisateurService; // Service pour gérer les utilisateurs
  private RoleService roleService; // Service pour gérer les rôles
  private PasswordEncoder passwordEncoder; // Utilitaire pour encoder les mots de passe

  private SpecialiteRepository specialiteRepository;

  @Autowired
  public DatabaseService(
      UtilisateurService utilisateurService,
      RoleService roleService,
      PasswordEncoder passwordEncoder,
      SpecialiteRepository specialiteRepository) {
    this.utilisateurService = utilisateurService;
    this.roleService = roleService;
    this.passwordEncoder = passwordEncoder;
    this.specialiteRepository = specialiteRepository;
  }

  public void initializeDatabase() {
    // Méthode pour initialiser la base de données avec des rôles et des utilisateurs par défaut

    System.out.println("Initializing database...");

    final Role roleAdmin = new Role(null, "admin");
    final Role roleClient = new Role(null, "prof");

    final Utilisateur user1 = new Utilisateur("admin@mail.com", passwordEncoder.encode("admin@mail.com"));
    final Utilisateur user2 = new Utilisateur("client@mail.com", passwordEncoder.encode("client@mail.com"));
    // Crée des objets de rôle et d'utilisateur avec des valeurs par défaut

    System.out.println(roleService.save(roleAdmin));
    System.out.println(roleService.save(roleClient));
    // Enregistre les rôles dans la base de données et obtient les identifiants générés

    user1.setRole(roleAdmin);
    user2.setRole(roleClient);
    // Associe les rôles aux utilisateurs

    System.out.println(utilisateurService.create(user1));
    System.out.println(utilisateurService.create(user2));
    // Enregistre les utilisateurs dans la base de données

    //

    final Specialite spec1 = new Specialite(null, "SISR");
    final Specialite spec2 = new Specialite(null, "SLAM");
    final Specialite spec3 = new Specialite(null, "SI");
    final Specialite spec4 = new Specialite(null, "Français");
    final Specialite spec5 = new Specialite(null, "EDM");

    System.out.println(specialiteRepository.save(spec1));
    System.out.println(specialiteRepository.save(spec2));
    System.out.println(specialiteRepository.save(spec3));
    System.out.println(specialiteRepository.save(spec4));
    System.out.println(specialiteRepository.save(spec5));

    System.out.println("Database initialized!");
    // Affiche un message pour indiquer que l'initialisation de la base de données est terminée
  }
}
