package otus.bdd.factory.implement;

import otus.bdd.exceptions.BrowserNotSupportedException;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.Config;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public interface IDriver {
  public EventFiringWebDriver newDriver();

  default void downloadLocalWebDriver(String browser) throws BrowserNotSupportedException {
    Config wdmConfig = WebDriverManager.getInstance().config();
    wdmConfig.setAvoidBrowserDetection(true);

    String browserVersion = System.getProperty("browser.version", "");

    if (!browserVersion.isEmpty()) {
      switch (browser) {
        case "chrome":
          wdmConfig.setChromeDriverVersion(browserVersion);
          break;
        default:
          throw new BrowserNotSupportedException(browser);
      }
    }
    WebDriverManager.getInstance(browser).setup();
  }
}

