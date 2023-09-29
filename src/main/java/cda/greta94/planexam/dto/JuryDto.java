package cda.greta94.planexam.dto;

import cda.greta94.planexam.model.Professeur;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

public class JuryDto {

    private Long id;

    @NotBlank(message = "Un numéro est requis")
    private String num;

    @NotNull(message = "Deux enseignants sont requis")
    private Professeur prof1;

    @NotNull(message = "Deux enseignants sont requis")
    private Professeur prof2;

    public JuryDto() {
    }

    public JuryDto(Long id, String num, Professeur prof1, Professeur prof2) {
        this.id = id;
        this.num = num;
        this.prof1 = prof1;
        this.prof2 = prof2;
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

    public Professeur getProf1() {
        return prof1;
    }

    public void setProf1(Professeur prof1) {
        this.prof1 = prof1;
    }

    public Professeur getProf2() {
        return prof2;
    }

    public void setProf2(Professeur prof2) {
        this.prof2 = prof2;
    }
}
