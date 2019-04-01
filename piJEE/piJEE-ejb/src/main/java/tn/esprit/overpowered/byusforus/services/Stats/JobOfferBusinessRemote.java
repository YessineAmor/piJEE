package tn.esprit.overpowered.byusforus.services.Stats;

import java.util.Date;
import java.util.Map;

import javax.ejb.Remote;

import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;

@Remote
public interface JobOfferBusinessRemote {
	public Integer countJO();
	public Map<JobOffer,Long> getMostJO(Integer limit,Date dateOfCreation , Date dateOfArchive);
	public Integer countQuizByPercentage(float percentage);
}
