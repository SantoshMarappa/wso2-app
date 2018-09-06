package org.schoolfinder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mock;
import org.schoolfinder.exception.CityNotFoundException;
import org.schoolfinder.exception.PostCodeNotFoundException;
import org.wso2.msf4j.Request;

public class SchoolFInderServiceImplTest {

	private SchoolFInderServiceImpl classUnderTest = new SchoolFInderServiceImpl();
	
	private Schools schools;
	
	@Mock
	private Request request;
	
	@Test
	public void testListOfSchools_whenListIsNotEmpty()
	{
		givenListOfSchool();
		whenListOfSchoolIsCalled();
		thenAssertListIsNotEmpty(2);
	}
	
	@Test
	public void testListOfSchools_whenListIsNull()
	{
		givenListOfSchoolIsEmpty();
		whenListOfSchoolIsCalled();
		thenAssertListIsNull();
	}
	
	@Test
	public void testGetSchoolsByCity_whenListIsNotEmpty() throws CityNotFoundException
	{
		givenListOfSchool();
		whenGetSchoolsByCityIsCalled("Coventry");
		thenAssertListIsNotEmpty(1);
	}
	
	@Test(expected = CityNotFoundException.class)
	public void testGetSchoolsByCity_whenListIsNotEmpty_givenWrongCity() throws CityNotFoundException
	{
		givenListOfSchool();
		whenGetSchoolsByCityIsCalled("Warwick");
	}
	
	@Test
	public void testGetSchoolsByPostCode_whenListIsNotEmpty() throws PostCodeNotFoundException
	{
		givenListOfSchool();
		whenGetSchoolsByPostCodeIsCalled("CV36 5LA");
		thenAssertListIsNotEmpty(1);
	}
	
	@Test(expected = PostCodeNotFoundException.class)
	public void testGetSchoolsByPostCode_whenListIsNotEmpty_givenWrongPostCode() throws PostCodeNotFoundException
	{
		givenListOfSchool();
		whenGetSchoolsByPostCodeIsCalled("Warwick");
		thenAssertListIsNotEmpty(0);
	}	
	
	private void givenListOfSchoolIsEmpty()
	{
		classUnderTest.listOfSchools = null;
	}
	
	private void givenListOfSchool()
	{
		classUnderTest.listOfSchools = new ArrayList<School>();
		School school = new School();
		school.setName("Park Hill");
    	school.setAddress("Lower Eastern Green Lane");
    	school.setPostCode("CV5 7LR");
    	school.setCity("Coventry");
    	school.setTelePhone("024 7646 6669");
    	school.setEmail("adminstaff@parkhill.coventry.sch.uk");   
    	classUnderTest.listOfSchools.add(school);
    	
		school = new School();
		school.setName("Acorns");
    	school.setAddress("Long Compton Shipston On Stour");
    	school.setPostCode("CV36 5LA");
    	school.setCity("Warwickshire");
    	school.setTelePhone("016 0868 4654");
    	school.setEmail("admin2639@welearn365.com");   
    	classUnderTest.listOfSchools.add(school);
	}
	
	private void whenListOfSchoolIsCalled()
	{
		schools = classUnderTest.ListAllSchools(request);
	}
	
	private void whenGetSchoolsByCityIsCalled(String city) throws CityNotFoundException
	{
		schools = classUnderTest.getSchoolsByCity(city);
	}
	
	private void whenGetSchoolsByPostCodeIsCalled(String postCode) throws PostCodeNotFoundException
	{
		schools = classUnderTest.getSchoolsByPostCode(postCode);
	}
	
	private void thenAssertListIsNotEmpty(int len)
	{
		assertEquals(len, schools.getSchools().size());
	}
	
	private void thenAssertListIsNull()
	{
		assertNull(schools.getSchools());
	}
}
