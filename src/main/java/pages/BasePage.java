package pages;
import base.FrameworkConfig;
import com.microsoft.playwright.Page;
import config.ConfigReader;
import config.Settings;

import static java.lang.System.getProperty;


public class BasePage {
    Page page = FrameworkConfig.LocalPage;
    // Constructor to initialize Page


    // Method to navigate to a specific URL
    public void navigate(String url) {
        page.navigate(url);
    }

    // Helper methods for navigating to specific global URLs
    public void navigateToLogin() {
        ConfigReader.PopulateSettings();
        String LoginUrl = Settings.LoginUrl;
        System.out.println(Settings.LoginUrl);

        FrameworkConfig.LocalPage.navigate(Settings.LoginUrl);
    }

    public void navigateToAddBook() {
        ConfigReader.PopulateSettings();
        String addBookUrl = Settings.AddBookUrl;
        FrameworkConfig.LocalPage.navigate(addBookUrl);
        navigate(addBookUrl);
    }
}