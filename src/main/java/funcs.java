import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class funcs {
    public static void main(String[] args) throws Exception {
        // Initializing the Driver:
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.w3schools.com/html/html_tables.asp");

        // Creating Function Variables:
        WebElement table = driver.findElement(By.xpath("//table[@id='customers']"));
        String searchText = "Island Trading";
        int searchColumn = 0;
        int returnColumnText = 2;
        String expectedText = "UK"; // Expected text in the specified return column

        // Running & Testing Functions:
        String result = getTableCellText(table, searchColumn, searchText, returnColumnText);
        System.out.println("(getTableCellText) Result: " + result);

        boolean result2 = verifyTableCellText(table, searchColumn, searchText, returnColumnText, expectedText);
        System.out.println("(verifyTableCellText) Result: " + result2);

        String result3 = getTableCellTextByXpath(table, searchColumn, searchText, returnColumnText);
        System.out.println("(getTableCellTextByXpath) Result: " + result3);

        driver.quit();
    }

    public static boolean verifyTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText, String expectedText) throws Exception {
        // Checks if the ExpectedText == ActualText in the table..
        String actualText = getTableCellText(table, searchColumn, searchText, returnColumnText);
        return actualText.equals(expectedText);
    }

    public static String getTableCellText(WebElement table, int searchColumn, String searchText, int returnColumnText) throws Exception {
        /**
         getTableCellText function return the element string value of a table element
         The element returned is positioned on the returnColumnText - column
         And in the searchColumn there is an element identified by searchText to find the element row
         @param table - the table we find the element on
         @param searchColumn - the column used to find the relevant row
         @param searchText - the element used to find the relevant row on the searchColumn column
         @param returnColumnText - the element column
         @return the value returned is the element according to the description.
         */
        int rowIndex = findRowIndex(table, searchColumn, searchText);

        if (rowIndex == -1) throw new Exception("Text not found in the specified column.");


        return getElement(table, rowIndex, returnColumnText);
    }

    public static int findRowIndex(WebElement table, int searchColumn, String searchText) {
        /**
         findRowIndex - the searchColumn there is an element identified by searchText to find the element row
         @param table - the table we find the element on
         @param searchColumn - the column used to find the relevant row
         @param searchText - the element used to find the relevant row on the searchColumn column
         @return the value returned is the relevant rowIndex
         */
        java.util.List<WebElement> rows = table.findElements(By.tagName("tr"));

        for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
            WebElement row = rows.get(rowIndex);
            java.util.List<WebElement> cells = row.findElements(By.tagName("td"));

            if (searchColumn >= 0 && searchColumn < cells.size()) {
                if (cells.get(searchColumn).getText().equals(searchText)) {
                    return rowIndex;
                }
            }
        }

        // Text not found in the specified column
        return -1;
    }

    public static String getElement(WebElement table, int rowIndex, int columnIndex) throws Exception {
        /**
         getElement function return the element string value of a table given its position in the table.
         @param rowIndex - the element row positioning
         @param columnIndex - the element column positioning
         @return the value returned is the element in the relevant position
         */
        java.util.List<WebElement> rows = table.findElements(org.openqa.selenium.By.tagName("tr"));
        if (rowIndex < 0 || rowIndex >= rows.size())
            throw new Exception("Invalid row index");

        WebElement row = rows.get(rowIndex);
        java.util.List<WebElement> cells = row.findElements(org.openqa.selenium.By.tagName("td"));
        if (columnIndex < 0 || columnIndex >= cells.size())
            throw new Exception("Invalid column index");

        WebElement cell = cells.get(columnIndex);
        return cell.getText();
    }

    public static String getTableCellTextByXpath(WebElement table, int searchColumn, String searchText, int returnColumnText) throws Exception {
        try {
            String searchXpath = String.format(".//tr/td[%d][contains(text(), '%s')]", searchColumn, searchText);
            String returnXpath = String.format(".//tr/td[%d]", returnColumnText);

            WebElement searchCell = table.findElement(By.xpath(searchXpath));
            WebElement returnCell = searchCell.findElement(By.xpath(returnXpath));

            return returnCell.getText();
        }catch (Exception e) {
            throw new Exception("Error occurred while retrieving table cell text: " + e.getMessage());
        }
    }
}

