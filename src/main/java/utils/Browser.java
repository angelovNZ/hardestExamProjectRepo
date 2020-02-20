package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

/**
 * Class which will build web browser and open an specific website.
 *
 * @author N Z Angelov
 */
public class Browser {
    public static WebDriver driver;

    /**
     * Setting web browser
     * It opens entered browser and maximizing the window
     *
     * @param browserName accepting the name of browser which will be set and opened.
     *                    Valid Options: firefox, chrome and opera.
     * @param url         accepting an website to open in the newly set browser
     */
    public static void openPage(String browserName, String url) {
        if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\webdrivers\\geckodriver-v0.26.0-win64\\geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get(url);
        }
        if (browserName.equalsIgnoreCase("opera")) {
            System.setProperty("webdriver.opera.driver", "C:\\webdrivers\\operadriver_win64\\operadriver_win64\\operadriver.exe");
            OperaOptions options = new OperaOptions();
            options.setBinary("C:\\Users\\N Angelov\\Desktop\\utils\\recources\\66.0.3515.36\\opera.exe");
            driver = new OperaDriver(options);
            driver.manage().window().maximize();
            driver.get(url);
        }
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get(url);
        }
    }

    /**
     * Quiting the web browser
     */
    public static void quitBrowser() {
        driver.quit();
    }

    /**
     * Closing only the currently opened page
     */
    public static void closePage() {
        driver.close();
    }
}
