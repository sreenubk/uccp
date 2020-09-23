package com.lotus.uccp.consentmgmt.dao;

import com.lotus.uccp.authentication.model.ControlGroup;
import org.jboss.jandex.PrimitiveType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ConrollControllerTests {

    @Autowired
    private ControlGroupDao controlGroupDao;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void saveTest(){

        ControlGroup cg = new ControlGroup();
        cg.setControlgroupname("Sinu");
        controlGroupDao.save(cg);;

    }

    @Test
    public void getlistTest(){

        List<String> names = controlGroupDao.getlist().stream()
                .map(ControlGroup::getControlgroupname).collect(Collectors.toList());
        assertThat(names, containsInAnyOrder("control_group_name"));

    }



}


