package e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;


public class  TestBase {
    //final-> Static
    protected static ApplicationManager app = new ApplicationManager(); // refactor длиная строчка// это создали екземпляр класа
    static Logger logger() {
        return LoggerFactory.getLogger(TestBase.class);
    }

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        logger().info("Setup chrome driver");
    }

    @BeforeMethod
    public void setupTest() {
        app.init();

    }@BeforeMethod
    public void startTest(Method m, Object[] p) {// ниже Arrays (выводить параметрихацию в log)
        logger().info("Start test " + m.getName() + " with data: " + Arrays.asList(p));// будет в лог показываться данные dataProvide
    }

    @AfterMethod
    public void tearDown() {
        app.stop();

    }

    @AfterMethod
    public void stopTest(ITestResult result) throws IOException {
        if(result.isSuccess()){
            logger().info("PASSED" + result.getMethod().getMethodName());
        }else{
            logger().info("FAILED" + result.getMethod().getMethodName() + "Screenshot path: " + app.takeScreenshot());
        }
    logger().info("=========================================================================");
}

    }
