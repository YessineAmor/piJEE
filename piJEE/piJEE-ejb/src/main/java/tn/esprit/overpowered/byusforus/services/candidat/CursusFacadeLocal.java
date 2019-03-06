/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.candidat;

import java.util.List;
import javax.ejb.Local;
import tn.esprit.overpowered.byusforus.entities.candidat.Cursus;

/**
 *
 * @author EliteBook
 */
@Local
public interface CursusFacadeLocal {

    void create(Cursus cursus);

    void edit(Cursus cursus);

    void remove(Cursus cursus);

    Cursus find(Object id);

    List<Cursus> findAll();

    List<Cursus> findRange(int[] range);

    int count();
    
}
