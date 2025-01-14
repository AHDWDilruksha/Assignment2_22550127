package TestCases;

import Base.BaseTest;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.SearchResultsPage;
import Utils.ExcelHandler;
import Utils.TakeErrorScreenShots;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AliExpressShareProduct extends BaseTest {

    @BeforeTest
    public void setup() {
        setUpBrowser();
    }

    @Test
    public void searchAndShareProduct() {

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
        setReportName("Share Product Scenario - Test Case 1");
        startTest("Share Product Scenario - Test Case 1");
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

        // Step 3: Share the product
        productPage.shareProduct("/html/body/div[12]/div[2]"); // Using the provided XPath for the share button
        test = extent.createTest("Share Product", "System successfully shared the product.");
        String screenshotPath3 = TakeErrorScreenShots.takeScreenshot(driver, "ShareProductAliExpress");
        test.pass("System successfully shared the product.")
                .addScreenCaptureFromPath(screenshotPath3);

        // Write data back to the Excel file
        excel.setCellData(1, 2, "Product Shared: " + productName, excelFilePath);

        // Close workbook
        excel.closeWorkbook();
    }
}
