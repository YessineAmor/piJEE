package tn.esprit.overpowered.byusforus.services.Stats;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.quiz.QuizTry;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.entities.util.ExpertiseLevel;

@Stateless
public class JobOfferBusiness implements JobOfferBusinessRemote, JobOfferBusinessLocal {
	@PersistenceContext(name="piJEE-ejb")
	private EntityManager em;
	@Override
	public Integer countJO() {
		int count = Math.toIntExact((Long)em.createQuery("SELECT count(j) FROM JobOffer j").getSingleResult());
		return count;
	}

	@Override
	public Integer countJOInPeriod(Date dateOfCreation, Date dateOfArchive) {
		// TODO Auto-generated method stub
		if (dateOfCreation==null)
			dateOfCreation = new Date(0);
		if (dateOfArchive==null)
			dateOfArchive= new Date();
		
		String query2 = "SELECT count(j) FROM JobOffer j WHERE "
				+ "j.dateOfCreation BETWEEN '" + new SimpleDateFormat("yyyy-MM-dd").format(dateOfCreation) + "' and '" + new SimpleDateFormat("yyyy-MM-dd").format(dateOfArchive)+"'"
				+" and "+ "j.dateOfArchive BETWEEN '" + new SimpleDateFormat("yyyy-MM-dd").format(dateOfCreation) + "' and '" + new SimpleDateFormat("yyyy-MM-dd").format(dateOfArchive)+"'";
		Integer count = Math.toIntExact((Long)em.createQuery(query2).getSingleResult());
		return count;
	}
	@Override
	public Integer countEventInPeriod(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		if (startDate==null)
			startDate = new Date(0);
		if (endDate==null)
			endDate= new Date();
		
		String query2 = "SELECT count(e) FROM Event e WHERE "
				+ "=e.startDate BETWEEN '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(startDate) + "' and '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(endDate)+"'"
				+" and "+ "t.endDate BETWEEN '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(startDate) + "' and '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(endDate)+"'";
		Integer count = Math.toIntExact((Long)em.createQuery(query2).getSingleResult());
		return count;
	}
	@Override
	public Integer countQuizByPercentage(float percentage) {
		float percentage1 = percentage;
		System.out.println(percentage1);
		int count = Math.toIntExact((Long)em.createQuery("SELECT count(q) FROM Quiz q WHERE q.percentage=:percentage").setParameter("idSeniority", percentage).getSingleResult());
		System.out.println(count);
		return count;
	}
	@Override
	public Double AVGQuizTryDays(Date startDate, Date finishDate) {
		// TODO Auto-generated method stub
		if (startDate==null)
			startDate = new Date(0);
		if (finishDate==null)
			finishDate= new Date();
		
		String query = "SELECT SUM(date(t.finishDate)-date(t.startDate)) FROM QuizTry t WHERE "
				+ "t.startDate BETWEEN '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(startDate) + "' and '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(finishDate)+"'"
		+" and "+ "t.dateEnd BETWEEN '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(startDate) + "' and '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(finishDate)+"'";
		
		Double sum = (Double)em.createQuery(query).getSingleResult();
		
		String query2 = "SELECT t FROM QuizTry t WHERE "
				+ "t.startDate BETWEEN '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(startDate) + "' and '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(finishDate)+"'"
				+" and "+ "t.finishDate BETWEEN '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(startDate) + "' and '" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(finishDate)+"'";
		List<QuizTry> listquiztry = em.createQuery(query2).getResultList();		
		System.out.println(sum);
		Double sum2=new Double(0);
		for (QuizTry quiztry : listquiztry) {
			sum2+=countWeekDaysStream(quiztry.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
					,quiztry.getFinishDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		}
		System.out.println("SUM 2 : "+sum2);
		return sum2; 
	}
	@Override
	public List<JobOffer> getJobOfferByPeople(Integer peopleNeeded, Date dateOfCreation, Date dateOfArchive) {
		// TODO Auto-generated method stub
		if (dateOfCreation==null)
			dateOfCreation = new Date(0);
		if (dateOfArchive==null)
			dateOfArchive= new Date();
		
		List<JobOffer> joboffer = em.createQuery("Select j from JobOffer j where j.peopleNeeded = :peopleNeeded"
		/*		 + "t.dateStart BETWEEN '"+ new SimpleDateFormat("yyyy-MM-dd").format(dateFrom) +"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(dateTo).toString()+"' "
				+ " and " + 
				"t.dateEnd BETWEEN '"+ new SimpleDateFormat("yyyy-MM-dd").format(dateFrom) +"' and '"+new SimpleDateFormat("yyyy-MM-dd").format(dateTo).toString()+"' "
			*/	).setParameter("peopleNeeded", peopleNeeded).getResultList();
		return joboffer;
	}
	
	
	
	/*@Override
	public Integer countQuizByJO(Long id) {
		// TODO Auto-generated method stub
		Integer count =  Math.toIntExact((Long)em.createQuery("SELECT count(q) FROM Quiz q , JobOffer j WHERE q.id = j.pkLevel.idResource AND l.pkLevel.idCompetence = :competence")
				.setParameter("competence", idCompetence).getSingleResult());
		return count;
	}*/
		private long countWeekDaysStream ( LocalDate start , LocalDate stop ) {
		    // Code taken from the Answer by Ravindra Ranwala.
		    // https://stackoverflow.com/a/51010738/642706
		    
			if (start.isAfter(stop)) {
			LocalDate inter=start;
			start=stop;
			stop=start;
			}
			long count = 0;
		    Set < DayOfWeek > weekend = EnumSet.of( DayOfWeek.SATURDAY , DayOfWeek.SUNDAY );
		    final long weekDaysBetween =  getDays(start, start.until(stop, ChronoUnit.DAYS)).filter( d -> ! weekend.contains( ((LocalDate)d).getDayOfWeek() ) ).count();
		    		//start.datesuntil(stop, ChronoUnit.DAYS)
		              //                       .filter( d -> ! weekend.contains( d.getDayOfWeek() ) )
		                //                     .count();
		    return weekDaysBetween;
		}
		
		public static Stream<LocalDate> getDays(LocalDate start, long days){
	        return Stream.iterate(start, date -> date.plusDays(1))
	                .limit(days+1);
	    }

   @Override
	public List<JobOffer> maplatlong() throws Exception {
		// TODO Auto-generated method stub
		
		List<JobOffer> listjoboffers = new ArrayList<>();
		TypedQuery<JobOffer> q = em.createQuery("SELECT j from JobOffer j",JobOffer.class);
		listjoboffers = q.getResultList();
		List<JobOffer> resultjoboffers = new ArrayList<>();
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();   
	    Date dateNow =format.parse(format.format(calendar.getTime()));
		
		for (JobOffer j : listjoboffers) {
			
		    //System.out.println(format.format(calendar.getTime()));
		    
		    //System.out.println(dateNow);
			Date dateOfCreation = j.getDateOfCreation();
		    Date dateOfArchive = j.getDateOfArchive();
		    
		    
		    //System.out.println(format.format(datebegin));
		    
		    long diffInMillies = Math.abs(dateNow.getTime() - dateOfCreation.getTime());
		    long totalDayPass = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		    
		    long diffInMillies1 = Math.abs(dateOfArchive.getTime() - dateOfCreation.getTime());
		    long peridoOfJobOffer = TimeUnit.DAYS.convert(diffInMillies1, TimeUnit.MILLISECONDS);
		    
		    System.out.println("Total Day Pass " + totalDayPass);
		    System.out.println("Period Of Project " + peridoOfJobOffer);
		    
		    double cout = ((double)totalDayPass/(double)peridoOfJobOffer)*100;
		    
		    DecimalFormat df = new DecimalFormat("##");
		    String dx=df.format(cout);
		    cout =Double.valueOf(dx);
		    System.out.println("Le Taux est " + cout);
		    
		
			
			
			String longitude = "";
			String latitude = "";
			String adress = j.getCity();
			
			int responseCode = 0;
		    String api = "https://maps.googleapis.com/maps/api/geocode/xml?key=AIzaSyCOISCpK2Xf8oSV3WAEIiJbYrs2rN5wgDE&address=" + URLEncoder.encode(adress, "UTF-8") + "&sensor=true";
		    URL url = new URL(api);
		    HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
		    httpConnection.connect();
		    responseCode = httpConnection.getResponseCode();
		    if(responseCode == 200)
		    {
		      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();;
		      Document document = builder.parse(httpConnection.getInputStream());
		      XPathFactory xPathfactory = XPathFactory.newInstance();
		      XPath xpath = xPathfactory.newXPath();
		      javax.xml.xpath.XPathExpression expr = xpath.compile("/GeocodeResponse/status");
		      String status = (String)expr.evaluate(document,XPathConstants.STRING);
		      if(status.equals("OK"))
		      {
		         expr =  xpath.compile("//geometry/location/lat");
		         latitude = (String)expr.evaluate(document, XPathConstants.STRING);
		         expr = xpath.compile("//geometry/location/lng");
		         longitude = (String)expr.evaluate(document, XPathConstants.STRING);
		         System.out.println(latitude);
		         System.out.println(longitude);
		         
		      }
		      else
		      {
		         throw new Exception("Error from the API - response status: "+status);
		      }
		    }
		  
		    Long Id = j.getId();
		    String city = j.getCity();
		    String description = j.getDescription();
		    Date dateOfCreation1 = j.getDateOfCreation();
		    Date dateOfArchive1 = j.getDateOfArchive();
		    String title = j.getTitle();
		    ExpertiseLevel expertiselevel = j.getExpertiseLevel();
		    CompanyProfile companyprofile = j.getCompany();
		    Integer peopleNeeded = j.getPeopleNeeded();
		    JobOffer jb=new JobOffer(Id, title, dateOfCreation1, description,city,dateOfArchive1,  expertiselevel,  peopleNeeded,  companyprofile,longitude,latitude);
		    
		    resultjoboffers.add(jb);
		    
		    
		    }
		
		
		
		return resultjoboffers;
	}
	

}
