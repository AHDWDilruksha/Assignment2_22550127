package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    // Locators using @FindBy
//    @FindBy(id = "gh-ac") // Search input field
    @FindBy(id = "search-words")
    WebElement searchBox;

//    @FindBy(id = "gh-btn") // Search button
    @FindBy(xpath = "//*[@id=\"_full_container_header_23_\"]/div[2]/div/div[1]/div/input[2]")
    WebElement searchButton;

    // Constructor to initialize elements
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to searchFor
    public void searchFor(String keyword) {
        searchBox.sendKeys(keyword);
        searchButton.click();
    }
}
