package com.lotus.uccp.consentmgmt.dao;

import com.lotus.uccp.authentication.model.AuthorizationGroup;
import com.lotus.uccp.authentication.model.OrganizationUnit;
import com.lotus.uccp.consentmgmt.dao.OrganizationUnitDao;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.util.*;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class OrganizationUnitTests {

//    Logger logger = (Logger) LogFactory.getLog(OrganizationUnitTests.class);

    @Autowired
    private OrganizationUnitDao organizationUnitDao;
    @Autowired
    ApplicationContext ctx;
    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    RowMapper<BigDecimal> idMapper = (rs, num) -> rs.getBigDecimal("organizationUnitid");

    @Test
    public void testContext(){

        entityManagerFactory.createEntityManager();
        String[] names  = ctx.getBeanDefinitionNames();

        List<String> fNamees = Arrays.stream(names).filter(name -> name.contains("entityManager")).collect(Collectors.toList());

           fNamees.     forEach(s -> System.out.println(s));
        assertTrue(fNamees.size() > 0);


    }

    @Test
    public void saveTest(){
        OrganizationUnit ou = new OrganizationUnit();
        AuthorizationGroup ag = new AuthorizationGroup();
        ag.setAuthorizationgroupid(new BigDecimal(1));
        Set<AuthorizationGroup> ags = new HashSet<AuthorizationGroup>();
        ags.add(ag);
        ou.setName("Accounts");
        ou.setComments("Accounts department");
        ou.setCreatedby("Sinu");
        ou.setRecordstatus("yes");
        ou.setAuthorizationGroups(ags);
        OrganizationUnit o = organizationUnitDao.save(ou);
        System.out.println(o.getComments());
        assertEquals("Sinu",o.getCreatedby());
    }

    @Test
    public void getlistTest(){

        List<String> names = organizationUnitDao.getlist().stream()
                .map(OrganizationUnit::getName).collect(Collectors.toList());
        assertThat(names, containsInAnyOrder("Sinu"));

    }

}
