package login.custumers.groups;

import login.LoginPage;
import login.customers.groups.CustomersGroupPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Browser;

public class CustomerGroupTests {

    @BeforeClass
    public void setUp() {
        LoginPage.openLoginPage();
        LoginPage.enterUsername("admin");
        LoginPage.enterPassword("parola123!");
        LoginPage.submit();
        LoginPage.isLoginSuccessful("Dashboard");
    }

    @Test
    public void successfullyCreatingGroup() throws InterruptedException {
        String groupName = "Takeda Group";

        CustomersGroupPage.goTo();

        while (CustomersGroupPage.isAnotherPageAvailable()) {
            if (!CustomersGroupPage.checkIfGroupNameExist(groupName)) {
                Thread.sleep(2000);
                CustomersGroupPage.goToNextPage();
            } if(CustomersGroupPage.checkIfGroupNameExist(groupName)){
                CustomersGroupPage.deleteGroup();
                CustomersGroupPage.isGroupDeleted();
                break;
            }
        }

        CustomersGroupPage.addGroup(groupName);
        CustomersGroupPage.isGroupCreated();
    }

    @AfterClass
    public void tearDown() {
        Browser.quitBrowser();
    }
}
