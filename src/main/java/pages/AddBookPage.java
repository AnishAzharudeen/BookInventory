package pages;

import base.FrameworkConfig;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import models.AddBookModel;

public class AddBookPage {

    Page page = FrameworkConfig.LocalPage;

    public Locator btnAddbook = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit Add New Book Form"));

    Locator titleInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Title:"));

    Locator authorInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Author"));

    Locator isbnInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("ISBN"));

    Locator pubdateInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Publication Date:"));

    Locator priceInput = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Price:"));

    Locator genreInput = page.getByLabel("Genre:");

    Locator submitAdd = page.getByLabel("Submit Add New Book Form");

    Locator errorMessage = page.getByRole(AriaRole.ALERT);

    private void fillInputField(Locator inputField, String value) {
        inputField.click();
        inputField.fill(value);
    }


    private void validateAndFill(Locator inputField, String value, String errorMessage) {
        if (value == null || value.isEmpty()) {
            // Instead of throwing exception, set error message
            System.out.println("Validation Error: " + errorMessage);
            FrameworkConfig.LocalPage.getByRole(AriaRole.ALERT).fill(errorMessage);
            return;
        }
        fillInputField(inputField, value);
    }
    public HomePage AddBook(String title, String author, String isbn, String pubdate, String price, String genre) {
        btnAddbook.click();
        fillInputField(titleInput, title);
        fillInputField(authorInput, author);
        fillInputField(isbnInput, isbn);
        fillInputField(pubdateInput, pubdate);
        fillInputField(priceInput, price);
        genreInput.click();

        genreInput.selectOption(new SelectOption().setLabel(genre));

        submitAdd.click();


        return new HomePage();
    }

    public HomePage AddBook(AddBookModel addBookModel) {
        btnAddbook.click();

        // Fill each field with null or empty check
        if (addBookModel.getTitle() != null && !addBookModel.getTitle().isEmpty()) {
            fillInputField(titleInput, addBookModel.getTitle());
        }

        if (addBookModel.getAuthor() != null && !addBookModel.getAuthor().isEmpty()) {
            fillInputField(authorInput, addBookModel.getAuthor());
        }

        if (addBookModel.getIsbn() != null && !addBookModel.getIsbn().isEmpty()) {
            fillInputField(isbnInput, addBookModel.getIsbn());
        }

        if (addBookModel.getPublished() != null && !addBookModel.getPublished().isEmpty()) {
            fillInputField(pubdateInput, addBookModel.getPublished());
        }

        if (addBookModel.getPrice() != null && !addBookModel.getPrice().isEmpty()) {
            fillInputField(priceInput, addBookModel.getPrice());
        }

        // Handle genre specifically with null or empty check
        if (addBookModel.getGenre() != null && !addBookModel.getGenre().isEmpty()) {
            genreInput.click();
            genreInput.selectOption(new SelectOption().setLabel(addBookModel.getGenre()));
        }

        // Submit the form and return to HomePage
        submitAdd.click();
        return new HomePage();
    }


    }






