package com.testvagrant.util;

/**
 * This interface contains all the XPATH constants to locate the specific
 * elements from Wikipedia web application.
 * @author Vrushali Shelar
 *
 */
public interface WikipediaXpathConstants {

	public static final String SEARCH_BOX_XPATH = "//input[@type='search']";
	public static final String SEARCH_BUTTON_XPATH = "//input[@id='searchButton']";
	public static final String RELEASE_DATE_LABEL_ELEMENT_XPATH = "//table/tbody/tr[12]/th/div";
	public static final String RELEASE_DATE_VALUE_XPATH = "//table/tbody/tr[12]/td/div/ul/li";
	public static final String ORIGIN_COUNTRY_VALUE_XPATH = "//table/tbody/tr[14]/td[text()='India']";
}
