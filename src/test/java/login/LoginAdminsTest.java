package login;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.Browser;

public class LoginAdminsTest {

    @DataProvider
    public Object[][] loginData() {
        return new String[][]{
                {"admin", "parola123!", "Dashboard"},
                {"admin1", "parola123!", "Dashboard"},
//                {"admin2", "parola123!", "Dashboard"},
//                {"admin3", "parola123!", "Dashboard"},
//                {"admin4", "parola123!", "Dashboard"},
//                {"admin5", "parola123!", "Dashboard"},
//                {"admin6", "parola123!", "Dashboard"},
//                {"admin7", "parola123!", "Dashboard"},
//                {"admin8", "parola123!", "Dashboard"},
//                {"admin9", "parola123!", "Dashboard"},
//                {"admin10", "parola123!", "Dashboard"},
//                {"admin11", "parola123!", "Dashboard"},
//                {"admin12", "parola123!", "Dashboard"},
//                {"admin13", "parola123!", "Dashboard"},
//                {"admin14", "parola123!", "Dashboard"},
//                {"admin15", "parola123!", "Dashboard"}
        };
    }

    @Test(dataProvider = "loginData")
    public void correctlyLoginWithAllAdminUsers(String adminUsername, String password, String expectedTitle) {
        LoginPage.openLoginPage();
        LoginPage.enterUsername(adminUsername);
        LoginPage.enterPassword(password);
        LoginPage.submit();
        if(LoginPage.isLoginSuccessful(expectedTitle)){
            System.out.println("Login is successful!");
        } else {
            Browser.quitBrowser();
            LoginPage.failTheTest();
        }
        LoginPage.userLogOut();
        LoginPage.userIsLogOut();
        Browser.quitBrowser();
    }
}
