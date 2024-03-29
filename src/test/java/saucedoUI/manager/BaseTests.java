package saucedoUI.manager;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;


public class BaseTests {

    protected final ApplicationManager app = new ApplicationManager();

    Logger logger = LoggerFactory.getLogger(BaseTests.class);

    @BeforeMethod
    public void setup() {
        app.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Allure.getLifecycle().addAttachment("screenshot","image/png","png",((TakesScreenshot) app.wd).getScreenshotAs(OutputType.BYTES));
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m) {
        logger.info("Start test" + m.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop test" + m.getName());
    }

}
