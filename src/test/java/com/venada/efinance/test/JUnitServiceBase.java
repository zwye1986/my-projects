package com.venada.efinance.test;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:controller.xml"})
public class JUnitServiceBase extends AbstractTransactionalJUnit4SpringContextTests{
	@Override
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}     
}
