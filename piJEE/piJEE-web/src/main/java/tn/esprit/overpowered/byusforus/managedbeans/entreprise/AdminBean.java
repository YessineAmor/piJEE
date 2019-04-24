/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.managedbeans.entreprise;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import tn.esprit.overpowered.byusforus.entities.authentication.Session;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.Event;
import tn.esprit.overpowered.byusforus.entities.users.CompanyAdmin;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.entities.users.Employee;
import tn.esprit.overpowered.byusforus.entities.users.HRManager;
import tn.esprit.overpowered.byusforus.entities.users.ProjectManager;
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

    //Elements for profile management to ensure login of various company staff members
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String userType;

    private Session adminSession;
    private CompanyAdmin compAdmin;
    private HRManager hrManager;
    private ProjectManager prManager;
    private Employee employee;
    private CompanyProfile company;
    private List<Event> events;
    private Event previewedEvent;
    private String eventName;
    private String eventLocation;
    private String eventDescription;
    private Date eventStartDate;
    private Date eventEndDate;

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
        userType = Authenticator.currentSession.getUser().getDiscriminatorValue();
        username= Authenticator.currentSession.getUser().getUsername();
        firstName= Authenticator.currentSession.getUser().getFirstName();
        lastName= Authenticator.currentSession.getUser().getLastName();
        email= Authenticator.currentSession.getUser().getEmail();
        switch (userType) {

            case "COMPANY_ADMIN":
                this.compAdmin = (CompanyAdmin) Authenticator.currentSession.getUser();
                this.company = compAdmin.getCompanyProfile();
                break;
            case "HUMAN_RESOURCE_MANAGER":
                this.hrManager = (HRManager) Authenticator.currentSession.getUser();
                this.company = hrManager.getCompanyProfile();
                break;
            case "PROJECT_MANAGER":
                this.prManager = (ProjectManager) Authenticator.currentSession.getUser();
                this.company = prManager.getCompanyProfile();
            default:
                this.employee = (Employee) Authenticator.currentSession.getUser();
                this.company = employee.getCompany();

        }
        this.adminSession = Authenticator.currentSession;

        
        //this.events = compAdminFacade.viewAllEvents();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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

    public HRManager getHrManager() {
        return hrManager;
    }

    public void setHrManager(HRManager hrManager) {
        this.hrManager = hrManager;
    }

    public ProjectManager getPrManager() {
        return prManager;
    }

    public void setPrManager(ProjectManager prManager) {
        this.prManager = prManager;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Date getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public Date getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public void doCompanyUpdate() {
        if (userType.equals("COMPANY_ADMIN")) {
            compAdminFacade.updateCompanyProfile(company);
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "!!", "No Enough Privilege For Such Action");
            FacesContext.getCurrentInstance().addMessage("!!", msg);
        }

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
        String goTo = "/views/front/adminEntreprise/compEventManagement?faces-redirect=true";
        previewedEvent = compAdminFacade.searchEventById(id);
        eventName = previewedEvent.getName();
        eventLocation = previewedEvent.getLocation();
        eventDescription = previewedEvent.getDescription();
        eventStartDate = previewedEvent.getStartDate();
        eventEndDate = previewedEvent.getEndDate();
        return goTo;
    }

    public String doDeleteEvent() {
        if (userType.equals("COMPANY_ADMIN")) {
            Event e = compAdminFacade.findEvent(eventName);
            if (e != null) {
                compAdminFacade.deleteEvent(e.getId());
                PrimeFaces.current().resetInputs(":infoForm");
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfull", "Event Deleted");
                FacesContext.getCurrentInstance().addMessage("Successfull", msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unable to delete", "This Event doesn't Exist");
                FacesContext.getCurrentInstance().addMessage("Unable", msg);
            }

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "!!", "No Enough Privilege For Such Action");
            FacesContext.getCurrentInstance().addMessage("!!", msg);
        }

        return this.viewEvents();
    }

    public String doUpdateEvent() {
        if (userType.equals("COMPANY_ADMIN")) {
            Event e = compAdminFacade.findEvent(eventName);
            if (e != null) {
                e.setLocation(eventLocation);
                e.setDescription(eventDescription);
                e.setStartDate(eventStartDate);
                e.setEndDate(eventEndDate);
                compAdminFacade.updateEvent(e);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfull", "Event Update");
                FacesContext.getCurrentInstance().addMessage("Successfull", msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", "This Event doesn't Exist");
                FacesContext.getCurrentInstance().addMessage("ERROR", msg);
            }

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "!!", "No Enough Privilege For Such Action");
            FacesContext.getCurrentInstance().addMessage("!!", msg);
        }

        return this.viewEvents();
    }

    public String doCreateEvent() {
        String goTo = "null";

        if (userType.equals("COMPANY_ADMIN")) {
            if (compAdminFacade.findEvent(eventName) != null) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "!!", "Event Name Already Exit");
                FacesContext.getCurrentInstance().addMessage("!!", msg);
            } else {
                Event newEvent = new Event();
                newEvent.setName(eventName);
                newEvent.setLocation(eventLocation);
                newEvent.setStartDate(eventStartDate);
                newEvent.setDescription(eventDescription);
                newEvent.setEndDate(eventEndDate);
                newEvent.setCompany(compAdmin.getCompanyProfile());
                compAdminFacade.createEvent(newEvent);
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "!!", "No Enough Privilege For Such Action");
            FacesContext.getCurrentInstance().addMessage("!!", msg);
        }

        return this.viewEvents();
    }
}
