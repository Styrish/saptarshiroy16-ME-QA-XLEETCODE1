package demo;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    WebDriver driver;
    WebDriverWait wait;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        //driver.close();
        driver.quit();

    }

    
    // TestCase01: Verify the Leetcode Homepage URL
    public  void testCase01(){
        System.out.println("Start Test case: testCase01");
        driver.get("https://leetcode.com/ ");

        String currentUrl = driver.getCurrentUrl();
        String expectedSubString = "leetcode";

        if (currentUrl.contains(expectedSubString)) {
            System.out.println("SUCCESS: Test Case 01 Passed!");
            System.out.println("Actual URL: " + currentUrl + " contains '" + expectedSubString + "'.");
        } else {
            System.err.println("FAILURE: Test Case 01 Failed!");
            System.err.println("Actual URL: " + currentUrl + " does NOT contain '" + expectedSubString + "'.");
        }

        System.out.println("end Test case: testCase01");
    }
    // TestCase02: Verify Problem Set URL and Display First 5 Questions
public void testCase02() throws InterruptedException {
    System.out.println("Start TestCase02");

    // Navigate to problemset page
    driver.get("https://leetcode.com/");
    driver.findElement(By.xpath("//p[text()='View Questions ']")).click();


    // Verify we’re on the problem‑set page
    String currentUrl = driver.getCurrentUrl();
    if (!currentUrl.contains("problemset")) {
        System.err.println("FAIL: URL does not contain 'problemset'");
        return;
    }
    System.out.println("SUCCESS: On problemset page");

   



    // 5. Print and verify the first five titles
    // printing titles

    // titles with name
    System.out.println("Printing first 5 questions:");
    for(int i=1;i<=5;i++){
                WebElement problem = driver.findElement(By.xpath("//a[@id='"+i+"']//div[contains(@class,'text-body')]"));
                String problemName = problem.getText().substring(3);
                problem.click();
                String title = driver.getTitle();
                if(title.contains(problemName)){
                    System.out.println("Problem "+i+" opened!");
                }
                driver.navigate().back();
                wait.until(ExpectedConditions.urlContains("problemset"));
            }
            Thread.sleep(2000);
            System.out.println("End Test case: testCase02");
        
}

public  void testCase03(){
    System.out.println("Start TestCase03");

    // Navigate to problemset page
    driver.get("https://leetcode.com/");
    driver.findElement(By.xpath("//p[text()='View Questions ']")).click();

    driver.findElement(By.xpath("//div[text()='1. Two Sum']")).click();

    String currentUrl = driver.getCurrentUrl();
        String expectedSubString = "two-sum";

        if (currentUrl.contains(expectedSubString)) {
            System.out.println("SUCCESS: Test Case 03 Passed!");
            System.out.println("Actual URL: " + currentUrl + " contains '" + expectedSubString + "'.");
        } else {
            System.err.println("FAILURE: Test Case 03 Failed!");
            System.err.println("Actual URL: " + currentUrl + " does NOT contain '" + expectedSubString + "'.");
        }

        System.out.println("end Test case: testCase03");
    

}
public void testCase04(){
        try{
            System.out.println("Start Test case: testCase04");
            driver.get("https://leetcode.com/problems/two-sum/");
        System.out.println("Navigated to problem page.");
        try {
            driver.findElement(By.id("onetrust-accept-btn-handler")).click();
            System.out.println("Cookie popup accepted.");
        } catch (Exception ignored) {}

        System.out.println("Forcing a 5-second wait for problem content to load...");
Thread.sleep(5000);
        By submissionsLocator = By.xpath("//div[contains(@class,'normal') and contains(text(),'Submissions')]");
        
        WebElement submissions = wait.until(
            ExpectedConditions.elementToBeClickable(submissionsLocator)
        );
        submissions.click();
        System.out.println("Clicked on Submissions tab.");

            // WebElement submissions = driver.findElement(By.xpath(
            //         "//div[contains(@class,'normal') and contains(text(),'Submissions')]"));
            // submissions.click();
            // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[contains(@href,'/accounts/login/?next=" +
                            "%2Fproblems%2Ftwo-sum%2Fsubmissions%2F')]")
            ));

            // String actualText = message.getText().trim();


            if (message.getText().contains("Register") && message.getText().contains("Log")) {
            System.out.println("SUCCESS: 'Register or Log in' message verified." );
        } else {
            System.err.println("FAILURE: Text mismatch. Found: " + message.getText());
        }
            System.out.println("End Test case: testCase04");
        }catch (Exception e){
        System.err.println("FAILURE: Test Case 04 failed due to: " + e.getMessage());
    }
}
}
