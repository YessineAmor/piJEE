/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.managedbeans.entreprise;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import tn.esprit.overpowered.byusforus.entities.authentication.Session;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.Event;
import tn.esprit.overpowered.byusforus.entities.users.CompanyAdmin;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.services.users.CompanyAdminFacadeRemote;
import util.authentication.Authenticator;

/**
 *
 * @author pc
 */
@ManagedBean(name = "adminBean")
@SessionScoped
public class AdminBean {

    /**
     * Creates a new instance of AdminBean
     */
    @EJB
    private CompanyAdminFacadeRemote compAdminFacade;

    private Session adminSession;
    private CompanyAdmin compAdmin;
    private CompanyProfile company;
    private List<Event> events;
    private Event previewedEvent;

    private UploadedFile file;
    private String fileName;

    public String getFileName() {
        fileName = file.getFileName();
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
        if (file != null) {
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
        this.company = compAdmin.getCompanyProfile();
        //this.events = compAdminFacade.viewAllEvents();
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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Event getPreviewedEvent() {
        return previewedEvent;
    }

    public void setPreviewedEvent(Event previewedEvent) {
        this.previewedEvent = previewedEvent;
    }

    public void doCompanyUpdate() {
        compAdminFacade.updateCompanyProfile(company);
        company = compAdminFacade.viewCompanyProfile(company.getId());
    }

    public String viewEvents() {
        String goTo = "null";
        events = compAdminFacade.viewAllEvents();
        if (events != null) {
            System.out.println("------Event:--" + events.get(0).getName());
            goTo = "/views/front/adminEntreprise/compEventManagement?faces-redirect=true";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "!!", "No Events found");
            FacesContext.getCurrentInstance().addMessage("!!", msg);
        }
        return goTo;
    }

    public String doPreviewEvent(Long id) {
        String goTo = "null";
        previewedEvent = compAdminFacade.searchEventById(id);
        return goTo;
    }

    public String doDeleteEvent() {
        compAdminFacade.deleteEvent(previewedEvent.getId());
        return this.viewEvents();
    }

    public String doUpdateEvent() {
        compAdminFacade.updateEvent(previewedEvent);
        return this.viewEvents();
    }

    public String doCreateEvent() {
        String goTo = "null";
        if (compAdminFacade.findEvent(previewedEvent.getName()) != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "!!", "Event Name Already Exit");
            FacesContext.getCurrentInstance().addMessage("!!", msg);
        }
        else {
        Event newEvent = new Event();
        newEvent.setName(previewedEvent.getName());
        newEvent.setLocation(previewedEvent.getLocation());
        newEvent.setStartDate(previewedEvent.getStartDate());
        newEvent.setEndDate(previewedEvent.getEndDate());
        newEvent.setCompany(compAdmin.getCompanyProfile());
       compAdminFacade.createEvent(newEvent);
       events= compAdminFacade.viewAllEvents();
        }
        return goTo;
    }
}
