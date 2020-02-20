package login.customers.groups;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.Browser;

import java.util.ArrayList;
import java.util.List;

/**
 * Class where will be checked all the groups from Customers Group list.
 * If there any group with the same name as given from the search it will be deleted and created as new one.
 * If there haven't got a group with the given name, it will be created.
 *
 * @author N Z Angelov
 */
public class CustomersGroupPage {

    private static final By RADIO_BUT_YES = By.xpath("//input[@value='1']");
    private static final By SAVE_BUTT = By.cssSelector(".pull-right button");
    private static final By SUCCESS_MESSAGE = By.cssSelector(".alert-success");
    private static final By ADD_BUTTON = By.cssSelector(".pull-right a");
    private static final By GROUP_NAME = By.cssSelector(".input-group input");
    private static final By DELETE_BUTT = By.cssSelector(".pull-right button");
    private static final By CUSTOMERS_MENU = By.id("menu-customer");
    private static final By CUSTOMER_GROUPS_OPTION = By.cssSelector("#collapse33 li:nth-of-type(2)");
    private static final By GROUPS_NAME = By.cssSelector("tbody tr");
    private static final By NEXT_PAGE_BUTT = By.xpath("//*[@class='pagination']//a[contains(text(),'>')] [not(contains(text(),'|'))]");

    private static List<String> groupsName = new ArrayList<String>();

    /**
     * It will go to Customers Group page
     */
    public static void goTo() {
        WebDriverWait wait = new WebDriverWait(Browser.driver, 10);
        wait.until(ExpectedConditions.visibilityOf(Browser.driver.findElement(By.id("menu"))));
        Browser.driver.findElement(CUSTOMERS_MENU).click();
        wait.until(ExpectedConditions.visibilityOf(Browser.driver.findElement(CUSTOMER_GROUPS_OPTION)));
        Browser.driver.findElement(CUSTOMER_GROUPS_OPTION).click();
    }

    /**
     * It will check if there exist's any group with the given group name.
     * Also will add all the groups name from the current page to list collection named 'groupsName'
     *
     * @param groupName Accepting String with a group name
     * @return true or false
     */
    public static boolean checkIfGroupNameExist(String groupName) {

        List<WebElement> allGroups = Browser.driver.findElements(GROUPS_NAME);

        for (WebElement currGroup : allGroups) {
            groupsName.add(currGroup.getText());

            if (currGroup.getText().contains(groupName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * It will add a new group to the Customers Group
     *
     * @param groupName Accepting String with group name
     */
    public static void addGroup(String groupName) {
        Browser.driver.findElement(ADD_BUTTON).click();

        WebDriverWait wait = new WebDriverWait(Browser.driver, 10);
        wait.until(ExpectedConditions.titleContains(Browser.driver.getTitle()));

        Browser.driver.findElement(GROUP_NAME).sendKeys(groupName);
        Browser.driver.findElement(RADIO_BUT_YES).click();
        Browser.driver.findElement(SAVE_BUTT).click();

    }

    /**
     * It will check if the group is successfully created by checking the message after creation of a new group.
     * The test will fail if the message is not displayed on the page.
     */
    public static void isGroupCreated() {
        if (Browser.driver.findElement(SUCCESS_MESSAGE).getText().contains("Success")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("=== The group is not successfully created ! ===");
        }
    }

    /**
     * It will delete the founded group which is depends form what answer is given from method checkIfGroupNameExist()
     * If false - nothing will be deleted
     * If true - the founded group will be deleted
     */
    public static void deleteGroup() {
        int numberOfGroups = getGroupsName().size();
        String elementOfCheckBox = ".table tbody tr:nth-of-type(" + numberOfGroups + ") td:nth-of-type(1)";

        Browser.driver.findElement(By.cssSelector(elementOfCheckBox)).click();

        Browser.driver.findElement(DELETE_BUTT).click();
        Browser.driver.switchTo().alert().accept();
    }

    /**
     * Will check if the group is successfully deleted by checking the message what it trows after that.
     * Using the method isGroupCreated() to take the message of deleting as is same as creating
     */
    public static void isGroupDeleted() {
        isGroupCreated();
    }

    /**
     * it will check if there is any other pages available to go next.
     *
     * @return true or false
     */
    public static boolean isAnotherPageAvailable() {
        boolean isThereAnyPage = true;
        List<WebElement> elements = Browser.driver.findElements(By.cssSelector(".pagination li"));
        List<String> lastElement = new ArrayList<String>();
        String activeClass;

        for (WebElement element : elements) {
            lastElement.add(element.getAttribute("class"));
        }

        activeClass = lastElement.get(lastElement.size() - 1).toString();

        if (activeClass.contains("active")) {
            isThereAnyPage = false;
        }
        return isThereAnyPage;
    }

    /**
     * Will go to next page by clicking on the next ' > ' button. But if there is no more pages available to go next
     * method will throw NoSuchElement exception, because will fail to find the next button.
     */
    public static void goToNextPage() {
        Browser.driver.findElement(NEXT_PAGE_BUTT).click();
        groupsName.clear();
    }

    // =========================================

    /**
     * Getter for the list collection consist of groups name of the current page
     *
     * @return Collection from type List consist Strings
     */
    public static List<String> getGroupsName() {
        return groupsName;
    }
}

