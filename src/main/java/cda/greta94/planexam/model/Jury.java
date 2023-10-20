package cda.greta94.planexam.model;

import jakarta.persistence.*;

@Entity
public class Jury {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String num;

    @ManyToOne
    @JoinColumn(name = "prof_1_id")
    private Professeur prof1;

    @ManyToOne
    @JoinColumn(name = "prof_2_id")
    private Professeur prof2;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "JOUR_ETAB_EPREUVE_ETABEPREUVEID", referencedColumnName = "etab_epreuve_id"),
            @JoinColumn(name = "JOUR_ETAB_EPREUVE_JOURID", referencedColumnName = "jour_id")
    })
    private JourEtabEpreuve jourEtabEpreuve;

    public JourEtabEpreuve getJourEtabEpreuve() {
        return jourEtabEpreuve;
    }

    public void setJourEtabEpreuve(JourEtabEpreuve jourEtabEpreuve) {
        this.jourEtabEpreuve = jourEtabEpreuve;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Professeur getProf2() {
        return prof2;
    }

    public void setProf2(Professeur prof2) {
        this.prof2 = prof2;
    }

    public Professeur getProf1() {
        return prof1;
    }

    public void setProf1(Professeur prof1) {
        this.prof1 = prof1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jury jury)) return false;

        if (getId() != null ? !getId().equals(jury.getId()) : jury.getId() != null) return false;
        return getNum() != null ? getNum().equals(jury.getNum()) : jury.getNum() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getNum() != null ? getNum().hashCode() : 0);
        return result;
    }
}
