package org.schoolfinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.schoolfinder.exception.CityNotFoundException;
import org.schoolfinder.exception.DuplicateSymbolException;
import org.schoolfinder.exception.InValidDataException;
import org.schoolfinder.exception.PostCodeNotFoundException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.wso2.msf4j.Microservice;
import org.wso2.msf4j.Request;
import org.xml.sax.SAXException;

/**
 * School Finder service. This service will be available at.
 * http://localhost:9090/schoolfinder
 */
@Path("/schoolfinder")
public class SchoolFInderServiceImpl implements SchoolFinderService, Microservice {

	public Collection<School> listOfSchools;
	
	public SchoolFInderServiceImpl()
	{
		listOfSchools = loadSchoolFromXML();
	}
	
    /**
     * Retrieve all schools.
     * http://localhost:9090/schoolfinder/all
     *
     * @return All schools will be sent to the client as Json/xml
     * according to the Accept header of the request.
     */
    @GET
    @Path("/all")
    @Produces({"application/json", "text/xml"})	
	public Schools ListAllSchools(@Context Request request) {
    	return new Schools(listOfSchools);
	}

    /**
     * Retrieve a school for a given city.
     * http://localhost:9090/schoolfinder/ByCity/Coventry
     *
     * @param city - will be taken from the path parameter.
     * 
     * @return All schools will be sent to the client as Json/xml
     * according to the Accept header of the request.
     */
    @GET
    @Path("/ByCity/{city}")
    @Produces({"application/json", "text/xml"})
	public Schools getSchoolsByCity(@PathParam("city") String city) throws CityNotFoundException {
    	System.out.println("Inside in getSchoolsByCity method with param : " + city);
		List<School> schoolByCity = new ArrayList<School>();
		for (School school : listOfSchools)
		{
			if(city !=null && school.getCity().equals(city))
			{
				schoolByCity.add(school);
			}
		}
		if (schoolByCity.isEmpty())
		{
			throw new CityNotFoundException("Schools in the city " + city + " not found");
		}
		return new Schools(schoolByCity);
	}

    /**
     * Retrieve a school for a given post code.
     * http://localhost:9090/schoolfinder/ByPostCode/CV5 7LR
     *
     * @param postcode - will be taken from the path parameter.
     * 
     * @return All schools will be sent to the client as Json/xml
     * according to the Accept header of the request.
     */
    @GET
    @Path("/ByPostCode/{postCode}")
    @Produces({"application/json", "text/xml"})    
	public Schools getSchoolsByPostCode(@PathParam("postCode") String postCode) throws PostCodeNotFoundException {
    	System.out.println("Inside in getSchoolsByPostCode method with param : " + postCode);
		List<School> schoolByPostCode = new ArrayList<School>();
		for (School school : listOfSchools)
		{
			if(postCode !=null && school.getPostCode().equals(postCode))
			{
				schoolByPostCode.add(school);
			}
		}
		if (schoolByPostCode.isEmpty())
		{
			throw new PostCodeNotFoundException("School for the postcode " + postCode + " not found");
		}
		return new Schools(schoolByPostCode);
	}
	
    /**
     * Add a new school.
     * curl -v -X POST -H "Content-Type:application/json" \
     * -d '{"name": "Acorns",
            "address": "Long Compton Shipston On Stour",
            "postCode": "CV36 5LA",
            "city": "Coventry",
            "email": "admin2639@welearn365.com",
            "telePhone": "016 0868 4654"}' \
     * http://localhost:9090/stockquote
     *
     * @param stock Stock object will be created from the request Json body.
     * @throws DuplicateSymbolException, InValidDataException 
     */
    @POST
    @Consumes("application/json")
    public void addSchool(School newSchool) throws DuplicateSymbolException, DuplicateSymbolException, InValidDataException {
    	System.out.println("Adding school ---" + newSchool);
        String postCode = newSchool.getPostCode();
        String name = newSchool.getName();
        if (postCode != null && name != null)
        {
			for (School school : listOfSchools)
			{
				if(school.getPostCode().equals(postCode) && school.getName().equals(name))
				{
					throw new DuplicateSymbolException("School already exists");
				}
			}
			listOfSchools.add(newSchool);
        }
        else
        {
        	throw new InValidDataException("Check the data being passed");
        }		
    }
    
	private Collection<School> loadSchoolFromXML()
	{
		listOfSchools = new ArrayList<School>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try 
		{
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse((getClass().getResourceAsStream("/Schools.xml")));
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("School");
            
            for (int temp = 0; temp < nList.getLength(); temp++) 
            {            	
            	Node nNode = nList.item(temp);            
                if (nNode.getNodeType() == Node.ELEMENT_NODE) 
                {   
                	School school = new School();
                	Element eElement = (Element) nNode;
                	school.setName(eElement.getElementsByTagName("Name").item(0).getTextContent());
                	school.setAddress(eElement.getElementsByTagName("Address").item(0).getTextContent());
                	school.setPostCode(eElement.getElementsByTagName("Postcode").item(0).getTextContent());
                	school.setCity(eElement.getElementsByTagName("City").item(0).getTextContent());
                	school.setTelePhone(eElement.getElementsByTagName("Telephone").item(0).getTextContent());
                	school.setEmail(eElement.getElementsByTagName("Email").item(0).getTextContent());   
                	listOfSchools.add(school);
                }
            }            
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		} catch (ParserConfigurationException ex) {
			ex.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		return listOfSchools;
	}
}
