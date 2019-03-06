/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.candidat;

import java.util.List;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.candidat.Certificate;

/**
 *
 * @author EliteBook
 */
@Remote
public interface CertificateFacadeRemote {

    void create(Certificate certificate);

    void edit(Certificate certificate);

    void remove(Certificate certificate);

    Certificate find(Object id);

    List<Certificate> findAll();

    List<Certificate> findRange(int[] range);

    int count();
    
}
