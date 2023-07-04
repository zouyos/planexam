package cda.greta94.planexam.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
  public class JourPassage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date datePassage;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    public Session getSession() {
      return session;
    }

    public void setSession(Session session) {
      this.session = session;
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
  }
