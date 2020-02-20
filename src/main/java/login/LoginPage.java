package login;

import org.openqa.selenium.By;
import org.testng.Assert;
import utils.Browser;

/**
 * Class which will log on to the pragmatic shop backend with admin user
 *
 * @author N Z Angelov
 */
public class LoginPage {


    private static final By USERNAME = By.id("input-username");
    private static final By PASSWORD = By.id("input-password");
    private static final By SUBMIT_BUTTON = By.xpath("//*[@class='btn btn-primary']");
    private static final By LOGOUT_BUTT = By.xpath("//*[contains(text(),'Logout')]");

    /**
     * Will start the chrome browser and open the backend of pragmatic shop page
     */
    public static void openLoginPage() {
        Browser.openPage("chrome", "http://shop.pragmatic.bg/admin/");
    }

    /**
     * It will enter the given username to that specific box on the page
     * @param username Accept String with the user name
     */
    public static void enterUsername(String username) {
        Browser.driver.findElement(USERNAME).sendKeys(username);
    }

    /**
     * It will enter the given password to that specific box on the page
     * @param password Accept String with the password
     */
    public static void enterPassword(String password) {
        Browser.driver.findElement(PASSWORD).sendKeys(password);
    }

    /**
     * It will click on Login button displayed on the login page
     */
    public static void submit() {
        Browser.driver.findElement(SUBMIT_BUTTON).click();
    }

    /**
     * It will check if the login is successful by taking the page title
     * @param expectedTitle Accept String with the expected Title which is have to be
     * @return true or false
     */
    public static boolean isLoginSuccessful(String expectedTitle) {
        return Browser.driver.getTitle().equalsIgnoreCase(expectedTitle);
    }

    /**
     * It will log out the admin user
     */
    public static void userLogOut(){
        Browser.driver.findElement(LOGOUT_BUTT).click();
    }

    /**
     * it will check if the admin user is log out successful by checking the page title which is have to be 'Administration'
     */
    public static void userIsLogOut(){
        Assert.assertEquals(Browser.driver.getTitle(),
                "Administration");
    }

    /**
     * It will just fail the test and print a message for that
     * Can be used if you want to fail the test at some point
     */
    public static void failTheTest() {
        Assert.fail("===  Something is wrong with the test!!  ===");
    }
}
