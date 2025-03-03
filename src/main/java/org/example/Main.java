package org.example;

import com.microsoft.playwright.*;

public class Main {

    public static void main(String[] args) throws Exception {
        //Navigation();
        NetworkInterception();
    }

    private static void Navigation() throws Exception {
        Playwright playwright = Playwright.create();
        BrowserType browserType = playwright.chromium();

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.devtools = true;
        launchOptions.headless = false;

        Browser browser = browserType.launch(launchOptions);

//        Browser.NewContextOptions newContextOptions = new Browser.NewContextOptions();
//        newContextOptions.withDevice(playwright.devices().get("iPhone 11 pro Max"));
        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.navigate("https://applicationforlibrarymanagementsystem.onrender.com/login");
        browser.close();
        playwright.close();
    }

    public static void NetworkInterception() throws Exception {
        Playwright playwright = Playwright.create();
        BrowserType browserType = playwright.chromium();

        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions();
        launchOptions.devtools = true;
        launchOptions.headless = false;

        Browser browser = browserType.launch(launchOptions);

        BrowserContext context = browser.newContext();
        Page page = context.newPage();

        page.route("**/*", route -> {
            if(route.request().resourceType().equalsIgnoreCase("image"))
                route.abort();
            else
                route.resume();
        });


        page.navigate("https://applicationforlibrarymanagementsystem.onrender.com/login");
        browser.close();
        playwright.close();
    }

}
