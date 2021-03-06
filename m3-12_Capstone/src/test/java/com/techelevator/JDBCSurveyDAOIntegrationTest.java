package com.techelevator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.npgeek.model.Survey;
import com.techelevator.npgeek.model.jdbc.JDBCSurveyDAO;

public class JDBCSurveyDAOIntegrationTest extends DAOIntegrationTest {

	private JDBCSurveyDAO dao;
	private JdbcTemplate jdbcTemplate;

	@Before
	public void setupTest() {
	    dao = new JDBCSurveyDAO(getDataSource());
	    jdbcTemplate = new JdbcTemplate(getDataSource());
	}

	@Test
	public void new_surveys_are_inserted_to_database() {
	    // Arrange
	    Survey theSurvey = getSurvey("CVNP", "testEmail@test.com", "Ohio", "inactive");
	    int count = jdbcTemplate.queryForObject("SELECT COUNT(activitylevel) FROM survey_result WHERE parkcode = 'CVNP'", Integer.class);
	   
	    // Act 1 : Insert
	    dao.save(theSurvey);
	    int newSize = jdbcTemplate.queryForObject("SELECT COUNT(activitylevel) FROM survey_result WHERE parkcode = 'CVNP'", Integer.class);
	    
	    // Assert
	    assertEquals(count + 1, newSize);
	}

	private Survey getSurvey(String parkcode, String email, String state, String activitylevel) {
	    Survey theSurvey = new Survey();
	    theSurvey.setCode(parkcode);
	    theSurvey.setEmail(email);
	    theSurvey.setState(state);
	    theSurvey.setActivityLevel(activitylevel);
	    return theSurvey;
	}
	
}