package base;
import com.microsoft.playwright.*;
public class BrowserInitialize {
    public Browser GetBrowser(String browserName, BrowserType.LaunchOptions launchOptions) {
        FrameworkConfig.Playwright = Playwright.create();
        BrowserType browserType = null;
        System.out.println("Browser Name: " + browserName);
        if(browserName.equalsIgnoreCase("chromium"))
            browserType = FrameworkConfig.Playwright.chromium();
        if(browserName.equalsIgnoreCase("firefox"))
            browserType = FrameworkConfig.Playwright.firefox();
        if(browserName.equalsIgnoreCase("webkit"))
            browserType = FrameworkConfig.Playwright.webkit();

        assert browserType != null;
        return browserType.launch(launchOptions);
    }

    public BrowserContext GetBrowserContext(Browser browser, Browser.NewContextOptions newContextOptions) {
        return browser.newContext(newContextOptions);
    }

    public Page GetPage(BrowserContext browserContext) { return browserContext.newPage(); }




}


