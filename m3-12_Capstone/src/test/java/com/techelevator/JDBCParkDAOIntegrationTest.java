package com.techelevator;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;


import com.techelevator.npgeek.model.Park;
import com.techelevator.npgeek.model.jdbc.JDBCParkDAO;

public class JDBCParkDAOIntegrationTest extends DAOIntegrationTest {

	private String parkCode;
	private static SingleConnectionDataSource dataSource;
	private JDBCParkDAO dao;
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setupTest() {
	    dao = new JDBCParkDAO(getDataSource());
	    jdbcTemplate = new JdbcTemplate(getDataSource());
	}
	
	@Test
	public void all_parks__get_listed() {
		
		//Arrange
	    int count = jdbcTemplate.queryForObject("SELECT count(*) FROM park", Integer.class);
	    
	    //Act
	    List<Park> results = dao.getAllParks();
	    
	    //Assert
	    assertEquals(count, results.size());
	}
	
}
