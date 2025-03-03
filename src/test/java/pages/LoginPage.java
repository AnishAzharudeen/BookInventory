package pages;

import base.FrameworkConfig;
import com.microsoft.playwright.Page;
import config.ConfigReader;
import config.Settings;
import models.LoginModel;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class LoginPage {

    Page page = FrameworkConfig.LocalPage;

    // Store the input field associated with the label "Username" in a variable
    Locator usernameInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter your username"));
    //store the password locator
    // Store the input field associated with the label "Username" in a variable
    Locator passwordInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Enter your password"));
   //Login Locator value
    Locator btnLogin= page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit login"));





    public void Login(String userName, String password) {
        usernameInput.click();
        usernameInput.fill(userName);
        passwordInput.click();
        passwordInput.fill(password);
        btnLogin.click();
        assertThat(page).hasTitle("Books Inventory App");
        System.out.println("Login Successful");


    }


    public void Login(LoginModel loginModel) {
        usernameInput.click();
        usernameInput.fill(loginModel.getUserName());
        passwordInput.click();
        passwordInput.fill(loginModel.getPassword());
        btnLogin.click();
        assertThat(page).hasTitle("Books Inventory App");


    }




}






