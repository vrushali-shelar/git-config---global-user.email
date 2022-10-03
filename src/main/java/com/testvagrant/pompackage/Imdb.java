package com.testvagrant.pompackage;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.testvagrant.service.MovieDetailsService;
import com.testvagrant.util.Constants;
import com.testvagrant.util.ImdbXpathConstants;
import com.testvagrant.util.Scripts;

/**
 * This is POM class for IMDB web application
 * 
 * @author Vrushali Shelar
 *
 */
public class Imdb implements MovieDetailsService {

	WebDriver driver;
	@FindBy(xpath = ImdbXpathConstants.SEARCH_BOX_XPATH)
	private WebElement searchBox;
	@FindBy(xpath = ImdbXpathConstants.SEARCH_MOVIE_BY_NAME_XPATH)
	private WebElement searchMovieByName;
	@FindBy(xpath = ImdbXpathConstants.DETAILS_CARD_XPATH)
	private WebElement title;
	@FindAll(@FindBy(how = How.XPATH, using = ImdbXpathConstants.RELEASE_DATE_XPATH))
	List<WebElement> releaseDateElement;
	@FindAll(@FindBy(how = How.XPATH, using = ImdbXpathConstants.ORIGIN_COUNTRY_XPATH))
	List<WebElement> originCountryElement;

	public Imdb(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public void searchByMovieName() {
		// TODO: take movie name as a input from user..
		searchBox.sendKeys("Pushpa: The Rise");
		searchBox.sendKeys(Keys.RETURN);
	}

	public void searchMovieAndClick() {
		searchMovieByName.click();
	}

	public void scrollToMovieDetailsCard() {
		JavascriptExecutor javascriptExecuter = (JavascriptExecutor) driver;
		javascriptExecuter.executeScript(Scripts.SCROLL_INTO_VIEW, title);
	}

	public String getReleaseDate() {
		String releaseDateValue = null;
		for (WebElement details : releaseDateElement) {
			String releaseDateTextData = details.getText();
			// split the string as it's returning label and value
			String releaseDateTextArr[] = releaseDateTextData.split(Constants.NEW_LINE_REGEX);
			releaseDateValue = releaseDateTextArr[1];
		}
		return releaseDateValue;
	}

	public String getOriginCountry() {
		String originCountryValue = null;
		for (WebElement details2 : originCountryElement) {
			String originCountryTextData = details2.getText();
			// split the string as it's returning label and value
			String[] originCountryTextDataArr = originCountryTextData.split(Constants.NEW_LINE_REGEX);
			originCountryValue = originCountryTextDataArr[1];
		}
		return originCountryValue;
	}

}
