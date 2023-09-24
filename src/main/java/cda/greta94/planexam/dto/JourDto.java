package cda.greta94.planexam.dto;

import java.sql.Date;

public class JourDto {

    private Long id;

    private Date datePassage;

    private Boolean ouvre;

    private Long epreuveId;

    public JourDto() {
    }

    public JourDto(Long id, Date datePassage, Long epreuveId, Boolean ouvre) {
        this.id = id;
        this.datePassage = datePassage;
        this.epreuveId = epreuveId;
        this.ouvre = ouvre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatePassage() {
        return datePassage;
    }

    public void setDatePassage(Date datePassage) {
        this.datePassage = datePassage;
    }

    public Boolean getOuvre() {
        return ouvre;
    }

    public void setOuvre(Boolean ouvre) {
        this.ouvre = ouvre;
    }

    public Long getEpreuveId() {
        return epreuveId;
    }

    public void setEpreuveId(Long epreuveId) {
        this.epreuveId = epreuveId;
    }
}
