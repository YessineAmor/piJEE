/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.users;
   
import java.util.List;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.HRManager;

/**
 *
 * @author pc
 */
@Remote
public interface HRManagerFacadeRemote {

    void create(HRManager hRManager);

    void edit(HRManager hRManager);

    void remove(HRManager hRManager);

    HRManager find(Object id);

    List<HRManager> findAll();

    List<HRManager> findRange(int[] range);

    int count();
    
    public boolean approveJobOffer(Long idJobOffer, String gmailPassword);
    
    public boolean declineJobOffer(Long idJobOffer, String gmailPassword, String motif);
    
    public Long createHRManager(HRManager hrManger);
    
    public boolean affecterHRtoCompany(Long hrManagerId, String compName);
}
