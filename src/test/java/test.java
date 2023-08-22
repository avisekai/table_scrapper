import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class test {

    public WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver(new ChromeOptions().addArguments("--headless")); // Headless mode for demonstration
        driver.get("https://www.w3schools.com/html/html_tables.asp");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testGetTableCellTextByXpath() {
        try {
            String searchText = "Island Trading";
            int searchColumn = 0;
            int returnColumnText = 2;
            WebElement table = driver.findElement(By.xpath("//table[@id='customers']"));
            String text = funcs.getTableCellText(table, searchColumn, searchText, returnColumnText);
            System.out.println("Returned Text: " + text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
