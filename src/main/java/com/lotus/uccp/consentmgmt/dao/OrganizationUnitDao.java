package  com.lotus.uccp.consentmgmt.dao;

import java.math.BigDecimal;
import java.net.IDN;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.lotus.uccp.authentication.model.OrganizationUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SuppressWarnings("JpaQlInspection")
@Repository
public class OrganizationUnitDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public OrganizationUnit save(OrganizationUnit organizationUnit) {
		entityManager.persist(organizationUnit);
		return organizationUnit;
	}

	public List<OrganizationUnit> getlist() {
		return entityManager.createQuery("select o from OrganizationUnit o", OrganizationUnit.class).getResultList();
	}

	public List<OrganizationUnit> getlistByGroupName(String name) {
		OrganizationUnit ou = entityManager.find(OrganizationUnit.class,name);
		ArrayList oul =  new ArrayList<>();
		oul.add(ou);
		return oul;
	}
	public OrganizationUnit getById(long id) {
		return entityManager.find(OrganizationUnit.class,id);
	}

	public Boolean update(OrganizationUnit organisationunit) {
		OrganizationUnit ou = entityManager.merge(organisationunit);
		return true;
	}

	public Boolean delete(long id) {
		OrganizationUnit ou = new OrganizationUnit();
		ou.setorganizationUnitid(new BigDecimal(id));
		entityManager.createQuery("delete o from organizationunit o", ou.getClass());
		return true;
	}
	 
	
}
