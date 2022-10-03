package com.testvagrant.util;

/**
 * This interface contains all the XPATH constants to locate the specific
 * elements from IMDB web application.
 * 
 * @author Vrushali Shelar
 *
 */
public interface ImdbXpathConstants {

	public static final String SEARCH_BOX_XPATH = "//input[@type='text']";
	public static final String SEARCH_MOVIE_BY_NAME_XPATH = "//a[text()='Pushpa: The Rise - Part 1']";
	public static final String DETAILS_CARD_XPATH = "//span[text()='Details']";
	public static final String RELEASE_DATE_XPATH = "//div//ul//li[@data-testid='title-details-releasedate']";
	public static final String ORIGIN_COUNTRY_XPATH = "//div//ul//li[@data-testid='title-details-origin']";
}
