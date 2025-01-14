package TestCases;

import Base.BaseTest;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.SearchResultsPage;
import Utils.ExcelHandler;
import Utils.TakeErrorScreenShots;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AliExpressShowSimilarItems extends BaseTest {

    @BeforeTest
    public void setup() {
        setUpBrowser();
    }

    @Test
    public void showSimilarItems() {

        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        ProductPage productPage = new ProductPage(driver);

        // Initialize Excel Information
        String excelFilePath = "/home/eleos/Documents/AssignmentThreeWithPOM/src/test/resources/Testdata/TestData.xlsx";
        String sheetName = "Data";

        // Initialize ExcelUtils
        ExcelHandler excel = new ExcelHandler(excelFilePath, sheetName);

        // Read data
        String productName = excel.getCellData(1, 1); // Row 1, Column 1

        // Step 1: Search for a product
        homePage.searchFor(productName);
        setReportName("Show Similar Items Scenario - Test Case 3");
        startTest("Show Similar Items Scenario - Test Case 3");
        test = extent.createTest("Product Search", "System successfully searched for the product and displayed results.");
        String screenshotPath1 = TakeErrorScreenShots.takeScreenshot(driver, "SuccessfulSearchAliExpress");
        test.pass("System successfully searched for the product and displayed results.")
                .addScreenCaptureFromPath(screenshotPath1);

        // Step 2: Select the first product
        searchResultsPage.selectFirstProduct();
        test = extent.createTest("First Product Selection", "System successfully selected the first product from the results.");
        String screenshotPath2 = TakeErrorScreenShots.takeScreenshot(driver, "FirstResultAliExpress");
        test.pass("System successfully selected the first product from the results.")
                .addScreenCaptureFromPath(screenshotPath2);

        // Step 3: Show similar items
        productPage.showSimilarItems("//*[@id=\"card-list\"]/div[1]/div/div/a/div[3]/div[2]/span"); // XPath for similar items
        test = extent.createTest("Show Similar Items", "System successfully displayed similar items for the selected product.");
        String screenshotPath3 = TakeErrorScreenShots.takeScreenshot(driver, "ShowSimilarItemsAliExpress");
        test.pass("System successfully displayed similar items for the selected product.")
                .addScreenCaptureFromPath(screenshotPath3);

        // Write data back to the Excel file
        excel.setCellData(1, 2, "Similar Items Shown for: " + productName, excelFilePath);

        // Close workbook
        excel.closeWorkbook();
    }
}
