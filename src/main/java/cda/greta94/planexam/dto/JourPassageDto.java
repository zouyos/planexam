package cda.greta94.planexam.dto;

import java.sql.Date;

public class JourPassageDto {

    private Long id;

    private Date datePassage;

    private Boolean ouvré;

    private Long sessionE5Id;

    public JourPassageDto() {
    }

    public JourPassageDto(Long id, Date datePassage, Long sessionE5Id, Boolean ouvré) {
        this.id = id;
        this.datePassage = datePassage;
        this.sessionE5Id = sessionE5Id;
        this.ouvré = ouvré;
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

    public Boolean getOuvré() {
        return ouvré;
    }

    public void setOuvré(Boolean ouvré) {
        this.ouvré = ouvré;
    }

    public Long getSessionE5Id() {
        return sessionE5Id;
    }

    public void setSessionE5Id(Long sessionE5Id) {
        this.sessionE5Id = sessionE5Id;
    }
}
