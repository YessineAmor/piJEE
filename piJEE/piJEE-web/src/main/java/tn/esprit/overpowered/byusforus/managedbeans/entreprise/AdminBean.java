/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.managedbeans.entreprise;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import tn.esprit.overpowered.byusforus.entities.authentication.Session;
import tn.esprit.overpowered.byusforus.entities.users.CompanyAdmin;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.services.users.CompanyAdminFacadeRemote;
import util.authentication.Authenticator;

/**
 *
 * @author pc
 */
@ManagedBean(name = "adminBean")
@ApplicationScoped
public class AdminBean {

    /**
     * Creates a new instance of AdminBean
     */
    
    @EJB
    private CompanyAdminFacadeRemote compAdminFacade;
    
    private Session adminSession;
    private CompanyAdmin compAdmin ;
    private CompanyProfile company;

        private UploadedFile file;
    private String fileName;
 
    public String getFileName() {
    	fileName= file.getFileName();
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() {
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
     
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    public CompanyAdminFacadeRemote getCompAdminFacade() {
        return compAdminFacade;
    }

    public void setCompAdminFacade(CompanyAdminFacadeRemote compAdminFacade) {
        this.compAdminFacade = compAdminFacade;
    }
    
     public AdminBean() {
        this.adminSession = Authenticator.currentSession;
        this.compAdmin = (CompanyAdmin) Authenticator.currentSession.getUser();
        this.company= compAdmin.getCompanyProfile();
    }

    public Session getAdminSession() {
        return adminSession;
    }

    public void setAdminSession(Session adminSession) {
        this.adminSession = adminSession;
    }

    public CompanyAdmin getCompAdmin() {
        return compAdmin;
    }

    public void setCompAdmin(CompanyAdmin compAdmin) {
        this.compAdmin = compAdmin;
    }

    public CompanyProfile getCompany() {
        return company;
    }

    public void setCompany(CompanyProfile company) {
        this.company = company;
    }
    
    public void doCompanyUpdate(){
        compAdminFacade.updateCompanyProfile(company);
        company= compAdminFacade.viewCompanyProfile(company.getId());
    }
}
