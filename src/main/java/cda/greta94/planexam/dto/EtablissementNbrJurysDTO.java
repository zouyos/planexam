package cda.greta94.planexam.dto;

import cda.greta94.planexam.model.Etablissement;
import cda.greta94.planexam.model.NbrJury;

import java.util.ArrayList;
import java.util.List;

public class EtablissementNbrJurysDTO {
    private Etablissement etablissement;
    private List<NbrJury> juries = new ArrayList<>();

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public List<NbrJury> getJuries() {
        return juries;
    }

    public void setJuries(List<NbrJury> juries) {
        this.juries = juries;
    }
}
