package com.freenow.ste.cc.pojo;


public class Address {
	
	private static String street;
	private static String suite;
	private static String city;
	private static String zipcode;
	private static Geo geo;
		
	/**
	 * @return the street
	 */
	public  String getStreet() {
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public  void setStreet(String street) {
		Address.street = street;
	}
	/**
	 * @return the suite
	 */
	public  String getSuite() {
		return suite;
	}
	/**
	 * @param suite the suite to set
	 */
	public  void setSuite(String suite) {
		Address.suite = suite;
	}
	/**
	 * @return the city
	 */
	public  String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public  void setCity(String city) {
		Address.city = city;
	}
	/**
	 * @return the zipcode
	 */
	public  String getZipcode() {
		return zipcode;
	}
	/**
	 * @param zipcode the zipcode to set
	 */
	public  void setZipcode(String zipcode) {
		Address.zipcode = zipcode;
	}
	/**
	 * @return the geo
	 */
	public  Geo getGeo() {
		return geo;
	}
	/**
	 * @param geo the geo to set
	 */
	public  void setGeo(Geo geo) {
		Address.geo = geo;
	}
	
	
	
	
	
}
