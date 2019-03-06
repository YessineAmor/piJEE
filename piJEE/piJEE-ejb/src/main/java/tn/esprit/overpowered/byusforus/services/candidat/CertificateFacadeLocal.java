/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.candidat;

import java.util.List;
import javax.ejb.Local;
import tn.esprit.overpowered.byusforus.entities.candidat.Certificate;

/**
 *
 * @author EliteBook
 */
@Local
public interface CertificateFacadeLocal {

    void create(Certificate certificate);

    void edit(Certificate certificate);

    void remove(Certificate certificate);

    Certificate find(Object id);

    List<Certificate> findAll();

    List<Certificate> findRange(int[] range);

    int count();
    
}
