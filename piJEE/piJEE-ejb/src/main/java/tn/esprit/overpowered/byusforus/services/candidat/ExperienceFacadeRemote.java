/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.candidat;

import java.util.List;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.candidat.Experience;

/**
 *
 * @author EliteBook
 */
@Remote
public interface ExperienceFacadeRemote {

    void create(Experience experience);

    void edit(Experience experience);

    void remove(Experience experience);

    Experience find(Object id);

    List<Experience> findAll();

    List<Experience> findRange(int[] range);

    int count();
    
    public Long createExp(Experience experience);
}
