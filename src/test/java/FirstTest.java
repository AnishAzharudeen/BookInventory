import base.FrameworkConfig;
import base.FrameworkInitialize;
import com.microsoft.playwright.Locator;
import config.ConfigReader;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AddBookPage;
import pages.LoginPage;

import com.microsoft.playwright.options.*;



public class FirstTest {

    @BeforeTest
    public void setupPlaywright() {
        ConfigReader.PopulateSettings();
        FrameworkConfig.LocalPage = new FrameworkInitialize().InitializePlaywright();

    }


    @BeforeTest
    public void testLogin() {
        FrameworkConfig.LocalPage.navigate("https://applicationforlibrarymanagementsystem.onrender.com/login");
        LoginPage loginPage = new LoginPage();
        loginPage.Login("admin1", "securePassword");


    }

    @Test
    public void testAddBook() {
        FrameworkConfig.LocalPage.navigate("https://applicationforlibrarymanagementsystem.onrender.com/add-book");
        AddBookPage addBookPage = new AddBookPage();
        addBookPage.AddBook("Book1", "Author1", "1234567890", "2022-01-01", "10.00", "Fantasy");
    }

    @Test
    public void testEmptyFieldsDisplayErrorMessage() {
        System.out.println("Running testEmptyFieldsDisplayErrorMessage");
        // Navigate to the page containing the form
        FrameworkConfig.LocalPage.navigate("https://applicationforlibrarymanagementsystem.onrender.com/add-book"); // Replace with your actual URL

        // Submit the form without filling in any fields
        Locator submitAdd = FrameworkConfig.LocalPage.getByLabel("Submit Add New Book Form"); // Adjust the selector for the submit button
        submitAdd.click();

        // Check and verify error messages for all required fields
        String[] requiredFields = {"Title", "Author", "ISBN", "Publication Date", "Price", "Genre"};
        for (String field : requiredFields) {
            String errorMessage = FrameworkConfig.LocalPage.getByRole(AriaRole.LIST)
                    .getByText(field + " is required.").innerText();
            Assert.assertTrue(errorMessage.contains(field + " is required."));
        }
    }

    @AfterTest
    public void cleanup() throws Exception  {
        FrameworkConfig.LocalPage.close();
        FrameworkConfig.Playwright.close();
    }

}
    