package tn.esprit.overpowered.byusforus.services.Paiement;



import java.util.List;

import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.Paiement.BankCard;
import tn.esprit.overpowered.byusforus.entities.Paiement.Cheque;
import tn.esprit.overpowered.byusforus.entities.Paiement.Paiment;
import tn.esprit.overpowered.byusforus.entities.Paiement.Virement;
import tn.esprit.overpowered.byusforus.entities.users.User;


@Remote
public interface PaimentRemote {
      public int remove(Paiment r);
      public int removeViement(Virement r);
     public int editViement(Virement r);
        public BankCard addBankcard(BankCard BankCard); 
        public Virement findByIdVirement(int id);
        public List<Virement> FindVirement();
	 public int editCheque(Cheque r) ;
        public Paiment findById(int id);
public Cheque findByIdCheque(int id) ;
	public List<Paiment> All();
        public List<Cheque> FindCheque(); 
        public int edit(Paiment r); 
        
	public List<Paiment> DisplayPaimentDeposit();

	public List<Paiment> DisplayPaimentMemberShip();

	public void EditStatusPaiment(Paiment p);

	public int AddPaiment(Paiment p);

	public Paiment DisplayPaimentHistorie(User u);

	public Long DisplayPaimentStatCotisationOk();

	public Long DisplayPaimentStatCotisationNo();

	public Cheque addCheque(Cheque cheque);

	public Virement addVirement(Virement virement);

	public List<Paiment> DisplayMyPaimentDeclaration(User u);

	public List<Paiment> DisplayMyPaimentDeposit(User u);

	public List<Paiment> DisplayMyPaimentMembership(User u);

}
