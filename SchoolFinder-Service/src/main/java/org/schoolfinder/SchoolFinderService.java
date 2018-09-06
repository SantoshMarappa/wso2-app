package org.schoolfinder;

import javax.ws.rs.core.Context;

import org.schoolfinder.exception.CityNotFoundException;
import org.schoolfinder.exception.DuplicateSymbolException;
import org.schoolfinder.exception.InValidDataException;
import org.schoolfinder.exception.PostCodeNotFoundException;
import org.wso2.msf4j.Request;

public interface SchoolFinderService 
{
	public Schools ListAllSchools(@Context Request request);
	
	public Schools getSchoolsByCity(String city) throws CityNotFoundException;
	
	public Schools getSchoolsByPostCode(String postCode) throws PostCodeNotFoundException;
	
	public void addSchool(School newSchool) throws DuplicateSymbolException, InValidDataException;
}
