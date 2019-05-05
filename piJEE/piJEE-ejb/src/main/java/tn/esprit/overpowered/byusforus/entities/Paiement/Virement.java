package tn.esprit.overpowered.byusforus.entities.Paiement;

import java.io.Serializable;
import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Null;

@Entity
public class Virement implements Serializable {

    private static final long serialVersionUID = 5L;

    @OneToOne(cascade={PERSIST, MERGE, REFRESH, DETACH})
    private Paiment paiment;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdVirement;
    private String bank;
    private int agence;
    private String codeVirement;

    public Paiment getPaiment() {
        return paiment;
    }

    public void setPaiment(Paiment paiment) {
        this.paiment = paiment;
    }

    public int getIdVirement() {
        return IdVirement;
    }

    public void setIdVirement(int idVirement) {
        IdVirement = idVirement;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public int getAgence() {
        return agence;
    }

    public void setAgence(int agence) {
        this.agence = agence;
    }

    public String getCodeVirement() {
        return codeVirement;
    }

    public void setCodeVirement(String codeVirement) {
        this.codeVirement = codeVirement;
    }

}
