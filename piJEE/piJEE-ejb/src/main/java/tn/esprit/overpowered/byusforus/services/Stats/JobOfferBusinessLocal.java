package tn.esprit.overpowered.byusforus.services.Stats;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;

@Local
public interface JobOfferBusinessLocal {
	public Integer countJO();
	public Integer countJOInPeriod(Date dateOfCreation, Date dateOfArchive) ;
	public Integer countEventInPeriod(Date startDate, Date endDate);
	public Integer countQuizByPercentage(float percentage);
	public Double AVGQuizTryDays(Date startDate, Date finishDate);
	public List<JobOffer> getJobOfferByPeople(Integer peopleNeeded, Date dateOfCreation, Date dateOfArchive);
}
