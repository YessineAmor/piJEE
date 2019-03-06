/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.entrepriseprofile;

import java.util.List;
import javax.ejb.Remote;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.CompanyAdmin;

/**
 *
 * @author pc
 */
@Remote
public interface CompanyAdminFacadeRemote {

    void create(CompanyAdmin companyAdmin);

    void edit(CompanyAdmin companyAdmin);

    void remove(CompanyAdmin companyAdmin);

    CompanyAdmin find(Object id);

    List<CompanyAdmin> findAll();

    List<CompanyAdmin> findRange(int[] range);

    int count();
    
}
