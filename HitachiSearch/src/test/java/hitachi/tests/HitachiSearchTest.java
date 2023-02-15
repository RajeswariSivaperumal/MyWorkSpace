package hitachi.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import hitachi.pages.HomePage;
import hitachi.utils.CommonLibrary;

public class HitachiSearchTest extends Hooks{
	HomePage homePage=new HomePage();

	@Test(enabled=true, description="Check whether correct URL launched")
	public void checkURL() throws IOException 
	{		
		String actual= homePage.verifyURL(driver);
		Assert.assertEquals(actual, CommonLibrary.getProperty("url"));
	}

	@Test(enabled=true, description="Check whether correct page title displayed")
	public void verifyPageTitle() throws IOException
	{		
		String actual= homePage.verifyPageTitle(driver);
		Assert.assertEquals(actual, CommonLibrary.getProperty("pageTitle"));
	}
	
	@Test(enabled=true, description="Check if search result displayed if match found for the keyword searched")
	public void searchKeyword() throws IOException
	{		
		homePage.clickSearchLink(driver);
		homePage.enterSeaarchKeyword(driver, "Electronics");
		homePage.clickSearchIcon(driver);
		String actualSearchResultheader = homePage.fetchSearchResultHeader(driver);
		String expectedSearchResultheader="Search results for: "+"Electronics";
		Assert.assertEquals(actualSearchResultheader, expectedSearchResultheader);
	}
	
	@Test(enabled=true, description="Check if default message displayed if no match found for the keyword searched")
	public void noSearchResults() throws IOException
	{		
		homePage.clickSearchLink(driver);
		homePage.enterSeaarchKeyword(driver, "hitachihitachihitachihitachihitachi");
		homePage.clickSearchIcon(driver);
		String actualSearchResultHeader = homePage.fetchSearchResultHeader(driver);
		String expectedSearchResultHeader="Sorry, your search didn't return any results.";
		Assert.assertEquals(actualSearchResultHeader, expectedSearchResultHeader);
	}

	@Test(enabled=true, description="Check if no pagination displayed if search result count is less than 10")
	public void nopagination() throws IOException
	{		
		homePage.clickSearchLink(driver);
		homePage.enterSeaarchKeyword(driver, "Chennai");
		homePage.clickSearchIcon(driver);
		int paginationSize = homePage.getPaginationSize(driver);
		Assert.assertTrue(paginationSize==0);
	}
	
	@Test(enabled=true, description="Check if total no. of search results matching with the count "
			+ "displayed in search header")
	public void verifySearchResultsCount() throws IOException
	{		
		homePage.clickSearchLink(driver);
		homePage.enterSeaarchKeyword(driver, "Chennai");
		homePage.clickSearchIcon(driver);
		int searchHeaderResultCount = homePage.getSearchResultCount(driver);
		int numberOfSearchResults = homePage.getNumberOfSearchResults(driver);
		Assert.assertEquals(searchHeaderResultCount, numberOfSearchResults);
	}
	
	@Test(enabled=true, description="Check if user navigated to correct page upon clicking on search result link")
	public void verifySearchResultHyperlinkNavigation() throws IOException
	{		
		homePage.clickSearchLink(driver);
		homePage.enterSeaarchKeyword(driver, "Chennai");
		homePage.clickSearchIcon(driver);
		String searchResultHyperlink = homePage.fetchSearchResultFromEachPage(driver).get(0);
		homePage.pageNavigation(driver, searchResultHyperlink);
		String actual= homePage.verifyURL(driver);
		Assert.assertEquals(actual, searchResultHyperlink);
	}
}
