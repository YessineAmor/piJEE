package tn.esprit.overpowered.byusforus.entities.Paiement; 
    import java.io.Serializable;
import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REFRESH;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BankCard implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    @OneToOne(cascade={PERSIST, MERGE, REFRESH, DETACH})
	private Paiment paiment;
	
	
	@Id
	private int IdBank;
	private int ExpirationDate;
	

	public Paiment getPaiment() {
		return paiment;
	}
	public void setPaiment(Paiment paiment) {
		this.paiment = paiment;
	}
	public int getIdBank() {
		return IdBank;
	}
	public void setIdBank(int idBank) {
		IdBank = idBank;
	}
	public int getExpirationDate() {
		return ExpirationDate;
	}
	public void setExpirationDate(int expirationDate) {
		ExpirationDate = expirationDate;
	}


}