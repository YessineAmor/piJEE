/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.entrepriseprofile;

import java.util.List;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;

/**
 *
 * @author pc
 */
@Remote
public interface JobOfferFacadeRemote {

    void create(JobOffer jobOffer);

    void edit(JobOffer jobOffer);

    void remove(JobOffer jobOffer);

    JobOffer find(Object id);

    List<JobOffer> findAll();

    List<JobOffer> findRange(int[] range);

    int count();
    
}
