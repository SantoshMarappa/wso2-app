package org.schoolfinder;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a school item. @XmlRootElement is used to support xml conversion.
 */
@SuppressWarnings("unused")
@XmlRootElement
public class School 
{
	private String name;
	private String address;
	private String postCode;
	private String city;
	private String email;
	private String telePhone;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}
	/**
	 * @param postCode the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the telePhone
	 */
	public String getTelePhone() {
		return telePhone;
	}
	
	/**
	 * @param telePhone the telePhone to set
	 */
	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}
	
	@Override
	public String toString()
	{
		return "Name : " + name + " Address : " + address + " Post Code : " + postCode + " City : " + city + " Email : " + email + " Telephone : " + telePhone;
	}
}
