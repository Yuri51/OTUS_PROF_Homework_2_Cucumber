package otus.bdd.factory;

import otus.bdd.exceptions.BrowserNotSupportedException;
import otus.bdd.factory.implement.ChromeWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class FactoryDriver {

  public EventFiringWebDriver getDriver(String browserName) throws BrowserNotSupportedException {
    switch (browserName) {
      case "chrome": {
        ChromeWebDriver chromeWebDriver = new ChromeWebDriver();
        chromeWebDriver.downloadLocalWebDriver(browserName);
        return new EventFiringWebDriver(new ChromeWebDriver().newDriver());
      }
      default:
        throw new BrowserNotSupportedException(browserName);
    }
  }
}


