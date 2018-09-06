package org.schoolfinder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for @{School}
 */
public class SchoolTest {

	private School classUnderTest = new School();
	
	@Test
	public void testProperties()
	{
		classUnderTest.setName("Park Hill");
		assertEquals("Park Hill", classUnderTest.getName());
		
		classUnderTest.setAddress("Lower Eastern Green Lane");
		assertEquals("Lower Eastern Green Lane", classUnderTest.getAddress());
		
		classUnderTest.setPostCode("CV5 7LR");
		assertEquals("CV5 7LR", classUnderTest.getPostCode());
		
		classUnderTest.setCity("Coventry");
		assertEquals("Coventry", classUnderTest.getCity());		
		
		classUnderTest.setTelePhone("024 7646 6669");
		assertEquals("024 7646 6669", classUnderTest.getTelePhone());		
		
		classUnderTest.setEmail("adminstaff@parkhill.coventry.sch.uk");
		assertEquals("adminstaff@parkhill.coventry.sch.uk", classUnderTest.getEmail());			
	}
}
