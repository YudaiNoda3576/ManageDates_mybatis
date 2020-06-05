package com.example.demo;

import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

@SpringBootTest
	@TestExecutionListeners({
	    TransactionalTestExecutionListener.class,
	    DependencyInjectionTestExecutionListener.class,
	    DbUnitTestExecutionListener.class
	})
	@Transactional
	@TestPropertySource(locations = "/application-test.properties")
	public abstract class BaseDatabaseTest {
	    private DataSourceDatabaseTester dataSourceDatabaseTester;

	    @Autowired
	    protected DataSource dataSource;

	    @BeforeEach
	    public void setUp() throws Exception {
	        dataSourceDatabaseTester = new DataSourceDatabaseTester(dataSource);
	    }

	    /**
	     * Seed data in to database
	     *
	     * @param dataSet dataSet
	     * @throws Exception Exception
	     */
	    protected void seedData(IDataSet dataSet) throws Exception {
	        dataSourceDatabaseTester.setDataSet(dataSet);
	        dataSourceDatabaseTester.onSetup();
	    }
	}


