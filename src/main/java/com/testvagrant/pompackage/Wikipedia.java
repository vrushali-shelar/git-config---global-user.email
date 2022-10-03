package com.testvagrant.pompackage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testvagrant.service.MovieDetailsService;
import com.testvagrant.util.Scripts;
import com.testvagrant.util.WikipediaXpathConstants;

/**
 * This is POM class for Wikipedia web application.
 * 
 * @author Vrushali Shelar
 *
 */
public class Wikipedia implements MovieDetailsService {
	WebDriver driver;
	@FindBy(xpath = WikipediaXpathConstants.SEARCH_BOX_XPATH)
	private WebElement searchBox;
	@FindBy(xpath = WikipediaXpathConstants.SEARCH_BUTTON_XPATH)
	private WebElement search;
	@FindBy(xpath = WikipediaXpathConstants.RELEASE_DATE_LABEL_ELEMENT_XPATH)
	private WebElement releaseDateLabelElement;
	@FindBy(xpath = WikipediaXpathConstants.RELEASE_DATE_VALUE_XPATH)
	private WebElement releaseDateTextValueElement;
	@FindBy(xpath = WikipediaXpathConstants.ORIGIN_COUNTRY_VALUE_XPATH)
	private WebElement originCountryValueElement;

	public Wikipedia(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void searchByMovieName() {
		// TODO: take movie name as a input from user..
		searchBox.sendKeys("Pushpa: The Rise");
	}

	public void clickSearchElement() {
		search.click();
	}

	public String getReleaseDateLabel() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(Scripts.SCROLL_INTO_VIEW, releaseDateLabelElement);
		return releaseDateLabelElement.getText();
	}

	public String getReleaseDate() {
		return releaseDateTextValueElement.getText();
	}

	public String getOriginCountry() {
		return originCountryValueElement.getText();
	}
}
