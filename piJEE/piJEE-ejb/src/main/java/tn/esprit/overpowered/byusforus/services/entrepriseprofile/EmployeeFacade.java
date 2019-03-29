/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.overpowered.byusforus.services.entrepriseprofile;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.overpowered.byusforus.entities.candidat.Experience;
import tn.esprit.overpowered.byusforus.entities.entrepriseprofile.JobOffer;
import tn.esprit.overpowered.byusforus.entities.users.Candidate;
import tn.esprit.overpowered.byusforus.entities.users.CompanyProfile;
import tn.esprit.overpowered.byusforus.entities.users.Employee;
import tn.esprit.overpowered.byusforus.entities.users.User;
import tn.esprit.overpowered.byusforus.entities.util.AbstractFacade;


/**
 *
 * @author pc
 */
@Stateless
public class EmployeeFacade extends AbstractFacade<Employee> implements EmployeeFacadeLocal, EmployeeFacadeRemote {

    @PersistenceContext(unitName = "piJEE-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeeFacade() {
        super(Employee.class);
    }

    @Override
    public Long createEmployee(Employee employee) {
        CompanyProfile company = em.find(CompanyProfile.class,
                employee.getCompany().getId());
        
        if(company != null){
        this.create(employee);
        return employee.getId();
        }
        
        return -1L;
    }

    @Override
    public void updateEmployee(Employee employee) {
        Employee emp = this.find(employee.getId());
        if(emp != null)
        {
            this.edit(employee);
        }
    }

    @Override
    public void deleteEmployee(Long employeeId) {
       Employee emp = this.find(employeeId);
        
        if(emp != null){
         this.remove(emp);
        }
       
    }

    @Override
    public Employee searchEmployeeById(Long employeeId) {
       return this.find(employeeId);
    }

    @Override
    public List<Employee> searchEmployeeByLastName(String name) {
         return em.createQuery("select e from Employee e where e.lastName"
                 + " LIKE CONCAT('%',:name,'%')", Employee.class)
                 .setParameter("name", name)
                 .getResultList();
    }

    @Override
    public List<Employee> searchEmployeeByPosition(String position) {
                List<Employee> employees = this.findAll();
        for (Employee emp : employees) {
            for (Experience exp : emp.getExperiences()) {
                if (exp.getPosition().equals(position)) {
                    employees.add(emp);
                }
            }
        }
        return employees;
    }

    @Override
    public Long subscribe(Long companyId, Long employeeId) {
        CompanyProfile company = em.find(CompanyProfile.class, companyId);
        Employee employee = this.find(employeeId);
        if(!employee.getSubscribedCompanies().contains(company) &&
                !(company.getSubscribers().contains(employee))){
        employee.getSubscribedCompanies().add(company);
        company.getSubscribers().add(employee);
        return company.getId();
        }
        return -1L;
    }

    @Override
    public List<JobOffer> customJobOfferList(Long employeeId) {
       return this.find(employeeId).getRegisteredOffers();
    }

    @Override
    public List<CompanyProfile> subscriptionList(Long employeeId) {
        return this.find(employeeId).getSubscribedCompanies();
    }
      
}
