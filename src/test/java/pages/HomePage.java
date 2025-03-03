package pages;

import base.FrameworkConfig;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;


public class HomePage {
    Page page = FrameworkConfig.LocalPage;
    Locator btnAddbook = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit Add New Book Form"));
    Locator btnLogout = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log out"));

    public AddBookPage ClickCreateNew() {
        btnAddbook.click();
        return new AddBookPage();
    }

    public  HomePage Logout() {
        btnLogout.click();
       return HomePage.this;
    }
}