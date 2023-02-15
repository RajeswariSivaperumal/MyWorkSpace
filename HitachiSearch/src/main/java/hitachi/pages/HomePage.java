package hitachi.pages;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage 
{
	WebDriverWait wait;
	
	private By searchLnk=By.id("open-global-search");
	private By searchTxt=By.id("site-search-keyword");
	private By searchIcon=By.xpath("//button[@aria-label='search']");
	private By searchResultHeader=By.xpath("//div[@class='results-header']/h4");
	private By pagination=By.xpath("//div[@class='search-results']//nav//a");
	private By searchResultCount=By.xpath("//div[@class='results-header']//strong[3]");
	private By paginationNextLnk=By.xpath("//div[@class='nav-links']//a[@class='next page-numbers']");
	private By searchResultHyperlinks=By.xpath("//div[@class='search-result']//a");
	
	/**
	 * Method to verify Current URL
	 * @param driver
	 * @return
	 */
	public String verifyURL(WebDriver driver)
	{
		return driver.getCurrentUrl();		
	}

	/**
	 * Method to verify current page title
	 * @param driver
	 * @return
	 */
	public String verifyPageTitle(WebDriver driver)
	{
		return driver.getTitle();
	}

	/**
	 * This method waits until search option is clickable and clicks on search
	 * @param driver
	 * @throws IOException 
	 */
	public void clickSearchLink(WebDriver driver) throws IOException 
	{		
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(searchLnk));
		driver.findElement(searchLnk).click();
	}

	/**
	 * This method enters keyword in the search text box
	 * @param driver
	 * @param keyword
	 */
	public void enterSeaarchKeyword(WebDriver driver, String keyword)
	{		
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchTxt));
		driver.findElement(searchTxt).sendKeys(keyword);
	}

	/**
	 * This Method clicks on the search icon
	 * @param driver
	 */
	public void clickSearchIcon(WebDriver driver)
	{
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(searchIcon));
		driver.findElement(searchIcon).click();
	}

	/**
	 * This method fetches search result header
	 * @param driver
	 * @return
	 */
	public String fetchSearchResultHeader(WebDriver driver)
	{	
		return driver.findElement(searchResultHeader).getText();	
	}

	/**
	 * This method check if pagination is available. Returns zero if no pagination
	 * @param driver
	 * @return
	 */
	public int getPaginationSize(WebDriver driver)
	{	
		return driver.findElements(pagination).size();
	}

	/**
	 * This method fetches the result count from search result header
	 * @param driver
	 * @return
	 */
	public int getSearchResultCount(WebDriver driver)
	{	
		return Integer.parseInt(driver.findElement(searchResultCount).getText());
	}

	/**
	 * This method fetches search result hyperlinks from each page
	 * @param driver
	 * @return
	 */
	public List<String> fetchSearchResultFromEachPage(WebDriver driver)
	{	
		List<String> searchResultLinks=new ArrayList<String>();

		if(fetchSearchResultHeader(driver).equalsIgnoreCase("Sorry, your search didn't return any results.") 
				== false && getSearchResultCount(driver)<=10)
		{
			List<WebElement> findElements = driver.findElements(searchResultHyperlinks);
			for (WebElement webElement : findElements) {
				searchResultLinks.add(webElement.getAttribute("href"));
			}
		}
		if(fetchSearchResultHeader(driver).equalsIgnoreCase("Sorry, your search didn't return any results.") 
				== false && getSearchResultCount(driver)>10)
		{
			while(driver.findElement(paginationNextLnk).isDisplayed())
			{
				driver.findElement(paginationNextLnk).click();
				List<WebElement> findElements = driver.findElements(searchResultHyperlinks);
				for (WebElement webElement : findElements) {
					searchResultLinks.add(webElement.getAttribute("href"));
				}
			}
		}
		return searchResultLinks;
	}

	/**
	 * This method gets the count of search results
	 * @param driver
	 * @return
	 */
	public int getNumberOfSearchResults(WebDriver driver)
	{			
		return fetchSearchResultFromEachPage(driver).size();
	}

	/**
	 * This method navigates to the page as per search result hyperlink clicked 
	 * @param driver
	 * @param hyperLink
	 */
	public void pageNavigation(WebDriver driver, String hyperLink)
	{			
		driver.findElement(By.xpath("//a[@href='"+hyperLink+"']")).click();
	}

}
