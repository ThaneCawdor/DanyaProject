package bookerAPI.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTests {

    Logger logger = LoggerFactory.getLogger(BaseTests.class);

    @BeforeMethod
    public void logTestStart(Method m) {
        logger.info("Start test" + m.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop test" + m.getName());
    }
}
