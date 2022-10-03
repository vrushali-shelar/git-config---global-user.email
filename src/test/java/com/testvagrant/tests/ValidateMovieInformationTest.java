package com.testvagrant.tests;

import java.time.Duration;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.testvagrant.pompackage.Imdb;
import com.testvagrant.pompackage.Wikipedia;
import com.testvagrant.util.Constants;

/**
 * This is the test class used to extract movie details from different sources
 * and validate the same.
 * 
 * @author Vrushali Shelar
 *
 */
public class ValidateMovieInformationTest {

	public static final Logger LOGGER = Logger.getLogger(ValidateMovieInformationTest.class.getName());
	private WebDriver driver;

	private WebDriverWait wait;

	@BeforeTest
	public void setupTest() {
		LOGGER.info("Test setup started..");
		System.setProperty(Constants.CHROME_DRIVER_KEY, Constants.CHROME_DRIVER_LOCATION);
		driver = new ChromeDriver();
		LOGGER.info("Setup completed..");
	}

	@Test
	public void SearchMovieInImdb() throws InterruptedException {
		// extract release date and origin country from Imdb..
		Imdb imdbPom = this.getMovieDetailsFromImdb(driver, Constants.IMDB_URL);
		String moviewReleaseDateFromImdb = imdbPom.getReleaseDate();
		String movieOriginCountryFromImdb = imdbPom.getOriginCountry();
		LOGGER.info(String.format("Extracted movie details from IMDB. Release Date: %s, Origin Country: %s",
				moviewReleaseDateFromImdb, movieOriginCountryFromImdb));
		// extract release date and origin country from wiki..
		Wikipedia wikiPom = this.getMovieDetailsFromWiki(driver, Constants.WIKIPEDIA_URL);
		String movieReleaseDateValueFromWiki = wikiPom.getReleaseDate();
		String moviewOriginCountryFromWiki = wikiPom.getOriginCountry();
		LOGGER.info(String.format("Extracted movie details from Wikipedia. Release Date: %s, Origin Country: %s",
				movieReleaseDateValueFromWiki, moviewOriginCountryFromWiki));
		Assert.assertEquals(movieOriginCountryFromImdb, moviewOriginCountryFromWiki,
				Constants.ORIGIN_COUNTRY_VALIDATION_FAIL);
		Assert.assertEquals(moviewReleaseDateFromImdb, movieReleaseDateValueFromWiki,
				Constants.RELEASE_DATE_VALIDATION_FAIL);
	}

	@AfterTest
	public void endTest() {
		LOGGER.info("End test started..");
		driver.close();
		driver.quit();
		LOGGER.info(Constants.MOVIE_INFORMATION_VAIDATION_OK);
	}

	private Imdb getMovieDetailsFromImdb(WebDriver driver, String url) throws InterruptedException {
		driver.get(url);
		Thread.sleep(500);
		Imdb imdbPom = new Imdb(driver);
		imdbPom.searchByMovieName();
		imdbPom.searchMovieAndClick();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ZERO);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div//ul//li[@data-testid='title-details-releasedate']")));
		imdbPom.scrollToMovieDetailsCard();
		return imdbPom;
	}

	private Wikipedia getMovieDetailsFromWiki(WebDriver driver, String url) throws InterruptedException {
		driver.get(url);
		Thread.sleep(2000);
		Wikipedia wikiPom = new Wikipedia(driver);
		wikiPom.searchByMovieName();
		wikiPom.clickSearchElement();
		wikiPom.getReleaseDateLabel();
		wait = new WebDriverWait(driver, Duration.ZERO);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody/tr[12]/td/div/ul/li")));
		Thread.sleep(5000);
		return wikiPom;
	}
}
