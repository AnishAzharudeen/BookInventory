package steps;

import base.FrameworkConfig;
import com.microsoft.playwright.Page;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.LoginModel;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;

import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
public class LoginSteps {
    @Given("I navigate to the application")
    public void iNavigateToTheApplication() {
        BasePage basePage = new BasePage();
        basePage.navigateToLogin();
        System.out.println("I navigated the application");

    }



    @DataTableType
    public LoginModel loginModel(Map<String, String> entry) {
        return LoginModel.createLoginModel(entry);
    }

    @And("I enter following login details")
    public void iEnterFollowingLoginDetails(List<LoginModel> loginModelList) {
        LoginPage loginPage = new LoginPage();
        loginPage.Login(loginModelList.stream().findFirst().get());



    }


    @Then("I should see welcome message")
    public void iShouldSeeWelcomeMessage(List<LoginModel> loginModelList) {

        String welcomeText = "Welcome " + loginModelList.stream().findFirst().get().getUserName();
        System.out.println("Welcome message: " + welcomeText);
        assert FrameworkConfig.LocalPage.getByText(welcomeText).isVisible();

       
    }

    @And("I enter invalid username and valid password")
    public void iEnterInvalidUsernameAndValidPassword(DataTable loginData) {
        System.out.println("I enter following login details");
        LoginPage loginPage = new LoginPage();
        loginPage.Login(loginData.cell(1,0), loginData.cell(1,1));
    }

    @Then("I should see error message")
    public void iShouldSeeErrorMessage() {
        assert FrameworkConfig.LocalPage.getByText("Invalid username or password.").isVisible();
    }

    @When("I click the logout button")
    public void iClickTheLogoutButton() {
        System.out.println("I clicked the logout button");
        HomePage homePage = new HomePage();
        homePage.Logout();
    }

    @Then("I should be redirected to the login page")
    public void iShouldBeRedirectedToTheLoginPage() {
        String currentUrl = FrameworkConfig.LocalPage.url();
        if (!currentUrl.contains("login")) {
            throw new AssertionError("Page did not redirect to the login page. Current URL: " + currentUrl);
        }
        System.out.println("Redirected to login page");
    }
    }

