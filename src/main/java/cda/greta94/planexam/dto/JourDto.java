package cda.greta94.planexam.dto;

import java.sql.Date;

public class JourDto {

    private Long id;

    private Date datePassage;

    private Boolean ouvre;

    private Long sessionE5Id;

    public JourDto() {
    }

    public JourDto(Long id, Date datePassage, Long sessionE5Id, Boolean ouvre) {
        this.id = id;
        this.datePassage = datePassage;
        this.sessionE5Id = sessionE5Id;
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

    public Long getSessionE5Id() {
        return sessionE5Id;
    }

    public void setSessionE5Id(Long sessionE5Id) {
        this.sessionE5Id = sessionE5Id;
    }
}
