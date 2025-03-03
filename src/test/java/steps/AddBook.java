package steps;

import base.FrameworkConfig;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.AddBookModel;
import models.ErrorMessageModel;
import org.testng.Assert;
import pages.AddBookPage;
import pages.BasePage;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

public class AddBook {
    @Then("I should see options to Addbook")
    public void iShouldSeeOptionsToAddbook() {
        FrameworkConfig.LocalPage.navigate("https://applicationforlibrarymanagementsystem.onrender.com/books");
        boolean isButtonVisible = FrameworkConfig.LocalPage.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions()
                .setName("Add Book")).isVisible();
        if (!isButtonVisible) {
            throw new AssertionError("Addbook button is not visible");
        }
    }

    @DataTableType
    public AddBookModel addBookModel(Map<String, String> entry) {
        return AddBookModel.createAddBookModel(entry);
    }

    @When("I enter the following book details")
    public void iEnterTheFollowingBookDetails(List<AddBookModel> AddBookModel) {
        FrameworkConfig.LocalPage.navigate("https://applicationforlibrarymanagementsystem.onrender.com/add-book");
        AddBookPage addBookPage = new AddBookPage();
        AddBookModel bookModel = AddBookModel.stream().findFirst().get();
        System.out.println("Author: " + bookModel.getAuthor());
        System.out.println("Title: " + bookModel.getTitle());
        System.out.println("ISBN: " + bookModel.getIsbn());
        System.out.println("Published: " + bookModel.getPublished());
        System.out.println("Price: " + bookModel.getPrice());
        addBookPage.AddBook(bookModel);

        if (FrameworkConfig.LocalPage.locator("div[role='alert']").isVisible()) {

            Map<String, String> fieldsToValidate = Map.of(
                    "Title", bookModel.getTitle() == null ? "" : bookModel.getTitle(),

                    "Author", bookModel.getAuthor() == null ? "" : bookModel.getAuthor(),
                    "ISBN", bookModel.getIsbn() == null ? "" : bookModel.getIsbn(),
                    "Publication Date", bookModel.getPublished() == null ? "" : bookModel.getPublished(),
                    "Price", bookModel.getPrice() == null ? "" : bookModel.getPrice(),
                    "Genre", bookModel.getGenre() == null ? "" : bookModel.getGenre()
            );


            fieldsToValidate.forEach((field, value) -> {
                if (value == null || value.isEmpty()) {
                    String expectedErrorMessage = field + " is required.";
                    String actualErrorMessage = FrameworkConfig.LocalPage.getByRole(AriaRole.LIST)
                            .getByText(expectedErrorMessage)
                            .innerText();
                    Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage));

                }
            });
        } else {
            String tableTitle = FrameworkConfig.LocalPage.getByRole(AriaRole.CELL,
                            new Page.GetByRoleOptions().setName(AddBookModel.stream().findFirst().get().getTitle()))
                    .innerText();
            FrameworkConfig.LocalPage.evaluate("window.tableTitle = '" + tableTitle + "'");
            System.out.println("Fetched Title from Booklist: " + tableTitle);
        }


    }


    @And("the book I added  should appear in the book list")
    public void theBookIAddedShouldAppearInTheBookList(List<AddBookModel> AddBookModel) {
        AddBookModel bookModel = AddBookModel.stream().findFirst().get();
        String tableTitle = FrameworkConfig.LocalPage.evaluate("window.tableTitle").toString();
        assertThat("The book title in the book list does not match the added book title!",
                tableTitle.equals(bookModel.getTitle()));
    }

    @DataTableType
    public ErrorMessageModel errorMessageEntry(Map<String, String> entry) {
        return ErrorMessageModel.createErrorModel(entry);
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage(List<ErrorMessageModel> ErrorMessageModel) {
        ErrorMessageModel errorMessageModel = ErrorMessageModel.stream().findFirst().get();


        String expectedErrorMessage = errorMessageModel.getFieldname() + " is required.";
        String actualErrorMessage = FrameworkConfig.LocalPage.getByRole(AriaRole.LIST)
                .getByText(expectedErrorMessage)
                .innerText();
        System.out.println("Expected Error Message: " + expectedErrorMessage);
        System.out.println("Actual Error Message: " + actualErrorMessage);
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage));

    }

    @Given("I am not authenticated")
    public void iAmNotAuthenticated() {

        BasePage basePage = new BasePage();
        basePage.navigateToLogin();

    }

    @When("I navigate to the {string} page")
    public void iNavigateToThePage(String arg0) {
        BasePage basePage = new BasePage();
        basePage.navigateToAddBook();
        AddBookPage addBookPage = new AddBookPage();
        Locator btnAddbook = addBookPage.btnAddbook;
        btnAddbook.click();
    }
}

