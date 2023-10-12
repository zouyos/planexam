package cda.greta94.planexam.service;

import cda.greta94.planexam.dao.JetonResetMdpRepository;
import cda.greta94.planexam.model.JetonResetMdp;
import cda.greta94.planexam.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class JetonResetMdpService {

    @Autowired
    private JetonResetMdpRepository jetonResetMdpRepository;

    public JetonResetMdp createToken(Utilisateur utilisateur) {
        JetonResetMdp passwordResetToken = new JetonResetMdp();
        passwordResetToken.setUtilisateur(utilisateur);
        passwordResetToken.setToken(this.generateToken());

        // Définir la date d'expiration (par exemple, 1/2 heure à partir de maintenant)
        LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(30);
        passwordResetToken.setExpiryDate(expiryDate);

        return jetonResetMdpRepository.save(passwordResetToken);
    }

    public JetonResetMdp findByToken(String jeton) {
        return jetonResetMdpRepository.findByToken(jeton);
    }

    public void deleteToken(JetonResetMdp jeton) {
        jetonResetMdpRepository.delete(jeton);
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
