/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.candidat;

import java.util.List;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.candidat.Candidate;

/**
 *
 * @author EliteBook
 */
@Remote
public interface CandidateFacadeRemote {

    void create(Candidate candidate);

    void edit(Candidate candidate);

    void remove(Candidate candidate);

    Candidate find(Object id);

    List<Candidate> findAll();

    List<Candidate> findRange(int[] range);

    int count();
    
}