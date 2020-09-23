package  com.lotus.uccp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@SpringBootApplication(scanBasePackages={"com.lotus.uccp"})
@Configuration
public class WebApplication extends SpringBootServletInitializer {
    
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }

//    @Autowired
//	EntityManagerFactory entityManagerFactory;
	
	@Bean
	public MBeanExporter exporter()
	{
	    final MBeanExporter exporter = new MBeanExporter();
	    exporter.setAutodetect(true);
	    exporter.setExcludedBeans("dataSource");
	    return exporter;
	}
//	@Bean
//	public EntityManager entityManager(){
//		return entityManagerFactory.createEntityManager();
//	}

    // --
    public static void main(String[] args) throws Exception {
    	
        SpringApplication.run(WebApplication.class, args);
    }
}
// http://10.52.1.31:9080/connect360-web/index.html#!/login
// mvn install:install-file -Dfile=C:/Connect360/db2jcc.jar -DgroupId=com.ibm.db2.jcc -DartifactId=db2jcc -Dversion=10.1 -Dpackaging=jar
// mvn install:install-file -Dfile=C:/Connect360/db2jcc4.jar -DgroupId=com.ibm.db2.jcc -DartifactId=db2jcc4 -Dversion=10.1 -Dpackaging=jar
// auth/getAuthRespData2/
//http://http://localhost:8090/index.html#!/login
// User1 , Password1
// http://localhost:8090/connect360.web/login.html
// http://9.42.158.19:8090/Connect360.Web/index.html#!/login http://9.42.158.19:8095/index.html#!/login
// http://9.42.158.19:7090/login.html
//http://localhost:8090/connect360-web/index.html#!/login
/*  Consent Group 
 *  http://localhost:8090/Connect360/consent/listConsentGroup/
 * 
 *  controlgroupname, organisationname and job 
 * 
 *  Search Auth Group -  http://localhost:8090/Connect360/auth/searchAuthGroup/
 *  AuthGroupList - http://localhost:8090/Connect360/auth/getListAuthGroup
 *  Save AuthGroup - http://localhost:8090/Connect360/auth/saveAuthGroup/
 *  Update AuthGroup - http://localhost:8090/Connect360/auth/updateAuthGroup/
 *  delete AuthGroup - http://localhost:8090/Connect360/auth/deleteAuthGroup/
 *  
 *  http://9.42.158.19:8090/Connect360/index.html#!/
 *  
 *  Org List - http://localhost:8090/Connect360/auth/getListOrg/
 *  Job List-  http://localhost:8090/Connect360/auth/getJobList/
 *  
 *  Role Security List - http://localhost:8090/Connect360/auth/getSRoleList/
 *  User List - http://localhost:8090/Connect360/auth/getUserList/
 *  
 *  http://localhost:8090/Connect360/index.html#!/
 *  Auth
 *  /auth/getJobList/
 *  /saveAuthGroup/
 *  
 *  http://localhost:8090/Connect360/auth/getListOrg/
 *  http://localhost:8090/Connect360/auth/getJobList/
 *  
 *  All Grp - http://localhost:8090/Connect360/control/listgrp/
 *  
 *   Control Group
 *   Search Group Name - http://localhost:8090/Connect360/control/listgrpwithgrpName/
 *   Delete Group - http://localhost:8090/Connect360/control/delControlGrp/
 *   Add Group - http://localhost:8090/Connect360/control/saveControlGrp/
 *   Update Group - http://localhost:8090/Connect360/control/updateControlGrp/
 *   Get Grp with Grp ID - http://localhost:8090/Connect360/control/getgrpWithGrpID/
 *   Get Constraint with Grp ID - http://localhost:8090/Connect360/control/getConstraint/
 *   Get Txn with Grp ID - http://localhost:8090/Connect360/control/getTxn/
 *   
 *   Transaction /updateTxn/
 *   Search = http://localhost:8090/Connect360/control/listAlltxn/
 *   Delete = http://localhost:8090/Connect360/control/deleteTxn/  
 *   ADD =    http://localhost:8090/Connect360/control/saveTxn/
 *   Update = http://localhost:8090/Connect360/control/updateTxn/
 *   
 *   Constraint
 *   Search = http://localhost:8090/Connect360/control/listAllConstraint/
 *   Delete = http://localhost:8090/Connect360/control/delConstraint/  
 *   ADD =    http://localhost:8090/Connect360/control/saveConstraint/
 *   Update = http://localhost:8090/Connect360/control/updateConstraint/
 */



/*  Post Method
 *  @RequestMapping(value = "/", method = RequestMethod.POST)
 *  public ResponseEntity<Car> update(@RequestBody Car car) { 
 * 
 * http://localhost:8090/Connect360/index.html
 *  /listAlltxn/
 *  http://websystique.com/spring-boot/spring-boot-rest-api-example 
 */
 /*  application.yaml
 * 	 Port changed - port: 8090
 *   1st page - http://localhost:8090/Connect360/index.html 
 *   /control --- delControlGrp
 *   Search Group Name - http://localhost:8090/Connect360/control/listgrpwithgrpName/Sri
 *   Delete Group Name - http://localhost:8090/Connect360/control/delControlGrp/108
 *   
 *   /updateControlGrp/{id}/{name}/{createdby}/{lastupdatedby}
 *   http://localhost:8090/Connect360/control/updateControlGrp/107/Group1
 *   http://localhost:8090/Connect360/control/listAlltxn/
 *   All Grp - http://localhost:8090/Connect360/control/listgrp/
 *   http://localhost:8090/Connect360/control/delControlGrp/{id}
 * e.g. - http://localhost:8090/Connect360/api/user/ 
 * 		 http://localhost:8090/connect360/api/Cosdabaccontrolgroup/
 *       modify ..http://localhost:8090/connect360/api/user/ 
 *       http://localhost:8090/Connect360/api/allgrp/ 
 *       http://9.42.158.19:8090/index.html#!
 * */