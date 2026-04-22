package utility;

import org.openqa.selenium.WebDriver;

public class Base {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
   // public static WebDriver driver;
    

    public static void setDriver(WebDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void unload() {
        driver.remove();
    }
}