/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.managedbeans.candidate;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.services.candidat.CandidateFacadeRemote;
import tn.esprit.overpowered.byusforus.services.entrepriseprofile.JobOfferFacadeRemote;
import util.authentication.Authenticator;

/**
 *
 * @author EliteBook
 */
@ManagedBean
@SessionScoped
public class CandidateViewController implements Serializable {

    private Candidate cdt;
    private String lastName;
    private String email;
    private String recommendations;
    private String firstName;
    private List<Candidate> friendRequests;
    private List<Candidate> friends;
    private List<Candidate> candidatesList;

    //Company
    private CompanyProfile selectedCompany;

    @EJB
    private CandidateFacadeRemote cdtFacade;

    private List<JobOffer> jobOffers;
    @EJB
    private JobOfferFacadeRemote jobFacade;

    public String subscribeToCompany() {
        String result;
        String goTo= "/views/candidate/companiesView?faces-redirect=true";
        result = cdtFacade.subscribe(selectedCompany.getId(), Authenticator.currentSession.getUser().getId());
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "!!", result);
        FacesContext.getCurrentInstance().addMessage("!!", msg);
        return goTo;
    }

    public String jobofferList() {
        jobOffers = jobFacade.findAll();
        return "/views/candidate/jobOfferView?faces-redirect=true";
    }

    public String candidatesList() {
        candidatesList = cdtFacade.afficherCandidats();
        return "/views/candidate/candidatesView?faces-redirect=true";
    }

    public String deleteFriend() {
        cdtFacade.deleteFriend(Authenticator.currentSession.getUser().getId(), cdt.getId());
        return this.friendList();
    }

    public String acceptFriendRequest() {
        cdtFacade.acceptFriendRequest(Authenticator.currentSession.getUser().getId(), cdt.getId());
        return this.friendRequestList();
    }

    public String friendList() {
        friends = cdtFacade.friendsList(Authenticator.currentSession.getUser().getId());
        return "/views/candidate/friendList?faces-redirect=true";
    }

    public String rejectFriendRequest() {
        cdtFacade.rejectFriendRequest(Authenticator.currentSession.getUser().getId(), cdt.getId());
        return this.friendRequestList();
    }

    public List<Candidate> getCandidates() {
        return cdtFacade.afficherCandidats();

    }

    public String cdtConnected() {
        cdt = cdtFacade.findCandidate(Authenticator.currentSession.getUser().getId());
        return "/views/candidate/profile?faces-redirect=true";
    }

    public void sendFriendRequest() {
        cdtFacade.sendFriendRequest(Authenticator.currentSession.getUser().getId(), cdt.getId());
    }

    public String friendRequestList() {
        friendRequests = cdtFacade.friendRequestList(Authenticator.currentSession.getUser().getId());
        return "/views/candidate/friendRequestList?faces-redirect=true";
    }

    public List<Candidate> pendingRequests() {
        return cdtFacade.pendingList(Authenticator.currentSession.getUser().getId());
    }

    public void recommendCandidate() {
        cdtFacade.recommend(cdt.getId());
    }

    public void incrementVisits() {
        cdtFacade.incrementVisits(cdt.getId());
    }

    public void recommend() {
        cdtFacade.recommend(cdt.getId());
    }

    public Candidate getCdt() {
        return cdt;
    }

    public void setCdt(Candidate cdt) {
        this.cdt = cdt;
    }

    public CandidateFacadeRemote getCdtFacade() {
        return cdtFacade;
    }

    public void setCdtFacade(CandidateFacadeRemote cdtFacade) {
        this.cdtFacade = cdtFacade;
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

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<Candidate> getFriendRequests() {
        return friendRequests;
    }

    public void setFriendRequests(List<Candidate> friendRequests) {
        this.friendRequests = friendRequests;
    }

    public List<Candidate> getFriends() {
        return friends;
    }

    public void setFriends(List<Candidate> friends) {
        this.friends = friends;
    }

    public List<Candidate> getCandidatesList() {
        return candidatesList;
    }

    public void setCandidatesList(List<Candidate> candidatesList) {
        this.candidatesList = candidatesList;
    }

    public List<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public void setJobOffers(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }

    public JobOfferFacadeRemote getJobFacade() {
        return jobFacade;
    }

    public void setJobFacade(JobOfferFacadeRemote jobFacade) {
        this.jobFacade = jobFacade;
    }

    public CompanyProfile getSelectedCompany() {
        return selectedCompany;
    }

    public void setSelectedCompany(CompanyProfile selectedCompany) {
        this.selectedCompany = selectedCompany;
    }

}
