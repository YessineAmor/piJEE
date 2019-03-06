/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.candidat;

import java.util.List;
import javax.ejb.Local;
import tn.esprit.overpowered.byusforus.entities.candidat.Visit;

/**
 *
 * @author EliteBook
 */
@Local
public interface VisitFacadeLocal {

    void create(Visit visit);

    void edit(Visit visit);

    void remove(Visit visit);

    Visit find(Object id);

    List<Visit> findAll();

    List<Visit> findRange(int[] range);

    int count();
    
}
