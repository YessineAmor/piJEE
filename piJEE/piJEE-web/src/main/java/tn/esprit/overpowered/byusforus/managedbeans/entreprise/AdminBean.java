/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.managedbeans.entreprise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import tn.esprit.overpowered.byusforus.entities.authentication.Session;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.Event;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.CompanyAdmin;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.entities.users.Employee;
import tn.esprit.overpowered.byusforus.entities.users.HRManager;
import tn.esprit.overpowered.byusforus.entities.users.ProjectManager;
import tn.esprit.overpowered.byusforus.entities.util.ExpertiseLevel;
import tn.esprit.overpowered.byusforus.entities.util.OfferStatus;
import tn.esprit.overpowered.byusforus.entities.util.Skill;
import tn.esprit.overpowered.byusforus.services.entrepriseprofile.JobOfferFacadeRemote;
import tn.esprit.overpowered.byusforus.services.users.CompanyAdminFacadeRemote;
import tn.esprit.overpowered.byusforus.services.users.HRManagerFacadeRemote;
import tn.esprit.overpowered.byusforus.services.users.ProjectManagerFacadeRemote;
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
    @EJB
    private JobOfferFacadeRemote jobOfferFacade;
    
    @EJB
    private HRManagerFacadeRemote hrmFacade;
    
    @EJB
    private ProjectManagerFacadeRemote prmFacade;
    
    //Elements for profile management to ensure login of various company staff members
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String userType;

    //Elements for Offers management
    private JobOffer previewedOffer;
    private List<JobOffer> offers;
    private JobOffer selectedOffer;
    private String offerTitle;
    private String offerCity;
    private int offerPeopleNeeded;
    private String offerDescription;
    private OfferStatus offerStatus;
    private Date offerDateOfArchive;
    private ExpertiseLevel offerExpertiseLevel;
    private List<Skill> offerSkills;
    private List<Skill> selectedSkills;

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
        offers = new ArrayList<JobOffer>();
                //offers = jobOfferFacade.viewAllOffers();

        offerSkills = new ArrayList<>();
        SelectItemGroup skills = new SelectItemGroup("Skills");
        skills.setSelectItems(new SelectItem[]{
            /*new SelectItem("C", (Skill.C).toString()),
            new SelectItem("C", (Skill.JAVA).toString()),
            new SelectItem("C", (Skill.MICROSOFT).toString()),
            new SelectItem("C", (Skill.NETWORKING).toString()),
            new SelectItem("C", (Skill.PYTHON).toString()),
            new SelectItem("C", (Skill.SOC).toString())*/
            new SelectItem("C", "C"),
            new SelectItem("C", "C")

        });

        //offerSkills.add(skills);
        offerSkills.add(Skill.C);
        offerSkills.add(Skill.JAVA);
        offerSkills.add(Skill.MICROSOFT);
        //offerSkills.add(Skill.C);
        /*
        System.out.println("Skills  ");
        for(Skill s : offerSkills)
            System.out.println(s);*/
        userType = Authenticator.currentSession.getUser().getDiscriminatorValue();
        username = Authenticator.currentSession.getUser().getUsername();
        firstName = Authenticator.currentSession.getUser().getFirstName();
        lastName = Authenticator.currentSession.getUser().getLastName();
        email = Authenticator.currentSession.getUser().getEmail();
        switch (userType) {

            case "COMPANY_ADMIN":
                this.compAdmin = (CompanyAdmin) Authenticator.currentSession.getUser();
                this.company = compAdmin.getCompanyProfile();

                break;
            case "HUMAN_RESOURCES_MANAGER":
                this.hrManager = (HRManager) Authenticator.currentSession.getUser();
                this.company = hrManager.getCompanyProfile();
                break;
            case "PROJECT_MANAGER":
                this.prManager = (ProjectManager) Authenticator.currentSession.getUser();
                this.company = prManager.getCompany();
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

    public JobOffer getPreviewedOffer() {
        return previewedOffer;
    }

    public void setPreviewedOffer(JobOffer previewedOffer) {
        this.previewedOffer = previewedOffer;
    }

    public List<JobOffer> getOffers() {
        return offers;
    }

    public void setOffers(List<JobOffer> offers) {
        this.offers = offers;
    }

    public JobOffer getSelectedOffer() {
        return selectedOffer;
    }

    public void setSelectedOffer(JobOffer selectedOffer) {
        this.selectedOffer = selectedOffer;
    }

    public String getOfferTitle() {
        return offerTitle;
    }

    public void setOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
    }

    public String getOfferCity() {
        return offerCity;
    }

    public void setOfferCity(String offerCity) {
        this.offerCity = offerCity;
    }

    public int getOfferPeopleNeeded() {
        return offerPeopleNeeded;
    }

    public void setOfferPeopleNeeded(int offerPeopleNeeded) {
        this.offerPeopleNeeded = offerPeopleNeeded;
    }

    
    public ProjectManagerFacadeRemote getPrmFacade() {
        return prmFacade;
    }

    public void setPrmFacade(ProjectManagerFacadeRemote prmFacade) {
        this.prmFacade = prmFacade;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }



    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public Date getOfferDateOfArchive() {
        return offerDateOfArchive;
    }

    public void setOfferDateOfArchive(Date offerDateOfArchive) {
        this.offerDateOfArchive = offerDateOfArchive;
    }

    public ExpertiseLevel getOfferExpertiseLevel() {
        return offerExpertiseLevel;
    }

    public void setOfferExpertiseLevel(ExpertiseLevel offerExpertiseLevel) {
        this.offerExpertiseLevel = offerExpertiseLevel;
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

    public JobOfferFacadeRemote getJobOfferFacade() {
        return jobOfferFacade;
    }

    public void setJobOfferFacade(JobOfferFacadeRemote jobOfferFacade) {
        this.jobOfferFacade = jobOfferFacade;
    }

    public HRManagerFacadeRemote getHrmFacade() {
        return hrmFacade;
    }

    public void setHrmFacade(HRManagerFacadeRemote hrmFacade) {
        this.hrmFacade = hrmFacade;
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
                newEvent.setCompany(company);
                compAdminFacade.createEvent(newEvent);
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "!!", "No Enough Privilege For Such Action");
            FacesContext.getCurrentInstance().addMessage("!!", msg);
        }

        return this.viewEvents();
    }
    //Offer Management

    public String viewOffers() {
        String goTo ;
        offers = jobOfferFacade.viewAllOffers();
        if (offers != null) {
            System.out.println("------Event:--" + offers.get(0).getTitle());
            goTo = "/views/front/adminEntreprise/compOfferManagement?faces-redirect=true";
        } else {
            offers.add(new JobOffer());
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "!!", "No Offer found");
            FacesContext.getCurrentInstance().addMessage("!!", msg);
            goTo = "/views/front/adminEntreprise/compOfferManagement?faces-redirect=true";
        }
        return goTo;
    }

    public String doCreateJobOffer() {
        String goTo = "null";

        switch (userType) {

            case "HUMAN_RESOURCES_MANAGER":
                if (jobOfferFacade.searchJobOfferByTitle(offerTitle) != null) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "!!", "Offer Title Already Exit");
                FacesContext.getCurrentInstance().addMessage("!!", msg);
            } else {
                JobOffer newOffer = new JobOffer();
                newOffer.setTitle(offerTitle);
                newOffer.setCity(offerCity);
                newOffer.setPeopleNeeded(offerPeopleNeeded);
                newOffer.setOfferStatus(OfferStatus.AVAILABLE);
                newOffer.setDescription(offerDescription);
                newOffer.setDateOfArchive(offerDateOfArchive);
                    System.out.println("Checking out skills:---"+selectedSkills.get(0));
                //newOffer.setCompany(company);
                //newOffer.sethRManager(hrManager);
                    System.out.println("CityLocation:--"+offerCity);
                    System.out.println("DescriptionDESSS:--"+offerDescription);
                hrmFacade.createOffer(hrManager.getId(), newOffer);
            }
                break;
            case "PROJECT_MANAGER":
                if (jobOfferFacade.searchJobOfferByTitle(offerTitle) != null) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "!!", "Offer Title Already Exit");
                FacesContext.getCurrentInstance().addMessage("!!", msg);
            } else {
                JobOffer newOffer = new JobOffer();
                newOffer.setTitle(offerTitle);
                newOffer.setCity(offerCity);
                newOffer.setPeopleNeeded(offerPeopleNeeded);
                newOffer.setOfferStatus(OfferStatus.PENDING);
                newOffer.setDescription(offerDescription);
                newOffer.setDateOfArchive(offerDateOfArchive);
               // newOffer.setCompany(company);
                prmFacade.createJobOfferRequest(newOffer, prManager.getId());
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "!!", "Your Job Offer Request Has Been Sent");
                FacesContext.getCurrentInstance().addMessage("!!", msg);
            }
                break;
            default:
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "!!", "No Enough Privilege For Such Action");
                FacesContext.getCurrentInstance().addMessage("!!", msg);
                break;
        }


        return this.viewOffers();
    }

    public List<Skill> getOfferSkills() {
        return offerSkills;
    }

    public void setOfferSkills(List<Skill> offerSkills) {
        this.offerSkills = offerSkills;
    }

    public Skill[] mySkills() {
        return Skill.values();
    }

    public List<Skill> getSelectedSkills() {
        return selectedSkills;
    }

    public void setSelectedSkills(List<Skill> selectedSkills) {
        this.selectedSkills = selectedSkills;
    }

}
