package checkout;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.deser.Deserializers.Base;

public class Search extends Base {

    private static final By driver = null;

	@DataProvider(name = "searchQueries")
    public Object[][] searchQueries() {
        return new Object[][]{
            {"jacket", true},
            {"invalidQuery12345", false},
            {"", false},
            {"@#$%^&*", false},
            {"longsearchquerywithlotsofcharacters", false},
            {"'; DROP TABLE users;--", false},
            {"<script>alert('XSS')</script>", false}
        };
    }

    @Test(dataProvider = "searchQueries")
    public void testSearch(String query, boolean expectResults) {
        performSearchTest(query, expectResults);
    }

    private void performSearchTest(String query, boolean expectResults) {
        WebElement searchBar = driver.findElement((SearchContext) By.id("search"));
        searchBar.clear();
        searchBar.sendKeys(query);
        searchBar.submit();

        FluentWait<WebDriver> wait = new WebDriverWait((WebDriver) driver, Duration.ofSeconds(10));
        WebElement results;

        try {
            results = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search.results")));
        } catch (Exception e) {
            results = null;
        } 

        if (expectResults) {
            Assert.assertNotNull(results, "Expected results for query: " + query);
        } else {
            Assert.assertTrue(results == null || results.getText().contains("Your search returned no results"), "Expected no results for query: " + query);
        }
    }
}
