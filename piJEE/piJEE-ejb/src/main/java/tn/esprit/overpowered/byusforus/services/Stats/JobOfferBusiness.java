package tn.esprit.overpowered.byusforus.services.Stats;

import java.util.Date;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;

@Stateless
public class JobOfferBusiness implements JobOfferBusinessRemote {
	@PersistenceContext(name="piJEE-ejb")
	private EntityManager em;
	@Override
	public Integer countJO() {
		int count = Math.toIntExact((Long)em.createQuery("SELECT count(j) FROM JobOffer j").getSingleResult());
		return count;
	}

	@Override
	public Map<JobOffer, Long> getMostJO(Integer limit, Date dateOfCreation, Date dateOfArchive) {
		JobOffer r = new JobOffer();
		Long count = new Long(0);
		if (dateOfCreation==null)
			dateOfCreation = new Date(0);
		if (dateOfArchive==null)
			dateOfArchive= new Date();
		//Split the Query into Conditions
				//explicit : => The DateStart AND DateEnd should be included
				//implicit : => The DateStart OR DateEnd should be included OR Bigger than DateStart AND DateEnd
		return null;
	}

	@Override
	public Integer countQuizByPercentage(float percentage) {
		float percentage1 = percentage;
		System.out.println(percentage1);
		int count = Math.toIntExact((Long)em.createQuery("SELECT count(q) FROM Quiz q WHERE q.percentage=:percentage").setParameter("idSeniority", percentage).getSingleResult());
		System.out.println(count);
		return count;
	}
	

}
