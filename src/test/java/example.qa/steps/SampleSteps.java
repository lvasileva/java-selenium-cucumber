package example.qa.steps;

import example.qa.Context;
import example.qa.pages.CartPage;
import example.qa.pages.CheckOutPage;
import example.qa.pages.ConfirmCheckOutPage;
import example.qa.pages.HomePage;
import example.qa.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class SampleSteps {
    LoginPage loginPage;
    HomePage homePage;

    CartPage cartPage;

    CheckOutPage checkOutPage;

    ConfirmCheckOutPage confirmCheckOutPage;

    @When("user opens a browser")
    public void userOpensABrowser() {
        //Nothing to do - the browser is open in Setup.before()
    }

    @When("user visits the site")
    public void userVisitsTheSite() {

        Context.getDriver().get("https://www.saucedemo.com/");
        loginPage = new LoginPage();
    }

    @And("login as {string} user")
    public void loginAsUser(String username) {


        homePage = loginPage.login(username, "secret_sauce");
    }

    @Then("user sees the list of items")
    public void homePageIsOpened() {


        Assert.assertTrue("Home page is not opened", homePage.IsListOfItemsPresented());
    }

    @Given("{string} user is logged in")
    public void isLoggedIn(String username) {

        userVisitsTheSite();
        loginAsUser(username);
    }

    @When("user add all items to the cart")
    public void userAddAllItemsToTheCart() {

        List<String> names = homePage.getAllItemNames();
        homePage.addSeveralItemsToShoppingCartByName(names);
    }

    @Then("user sees {int} items added to the cart")
    public void userSeesItemsAddedToTheCart(int numberOfItems) {

        Assert.assertEquals("Incorrect number of items. Expected: " + numberOfItems + ", but was homePage.getNumberOfItemsInTheShoppingCart()", numberOfItems, homePage.getNumberOfItemsInTheShoppingCart());
    }

    @And("user opens the cart")
    public void userOpenedTheCart() {

        cartPage = homePage.openShoppingCart();
    }

    @And("user proceeds to checkout from the cart")
    public void userProceedsToCheckoutFromTheCart() {

        checkOutPage = cartPage.checkOut();
    }

    @And("user continue checkout with the following info")
    public void userProceedsToCheckoutWithTheFollowingInfo(DataTable tbl) {

        String firstName = "";
        String lastName = "";
        String postalCode = "";

        List<Map<String, String>> rows = tbl.asMaps(String.class, String.class);

        for (Map<String, String> columns : rows) {
            String field = columns.get("field");
            String value = columns.get("value");

            switch (field.toLowerCase()) {

                case "first name":
                    firstName = value;
                    break;

                case "last name":
                    lastName = value;
                    break;

                case "postal code":
                    postalCode = value;
                    break;
                default:
                    Assert.fail("Unknown incoming field [ " + field + " ]");
                    break;
            }

        }

        confirmCheckOutPage = checkOutPage.fillFormAndContinue(firstName, lastName, postalCode);
    }

    @Then("user sees the total value equals to {float} dollars")
    public void userSeesTheTotalValueEqualsTo(float total) {
        Assert.assertEquals("Incorrect total value. Expected: " + total + ", but it was: " + confirmCheckOutPage.getTotalValue(), total, confirmCheckOutPage.getTotalValue(), 0.001);
    }

    @But("user delete all the {string} from the cart")
    public void userDeleteAllTheFromTheCart(String substring) {
        List<String> names = cartPage.getAllNamesBySubstring(substring);
        cartPage.removeSeveralItemsFromShoppingCartByName(names);
        checkOutPage = cartPage.checkOut();

    }
}
