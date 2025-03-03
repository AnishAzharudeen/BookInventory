package steps;

import base.FrameworkConfig;
import base.FrameworkInitialize;
import config.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.testng.annotations.Test;


public class Hooks {

    @Before
    public void setup() {

        ConfigReader.PopulateSettings();
        FrameworkConfig.LocalPage = new FrameworkInitialize().InitializePlaywright();


    }

    @After
    public void cleanup() throws Exception {
        FrameworkConfig.LocalPage.close();
        FrameworkConfig.Playwright.close();
    }

}

