package otus.bdd.pageobject;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import otus.bdd.support.UIGuiceScoped;
import otus.bdd.waiters.Waiters;
import java.util.List;

public abstract class AbsPageObject<T> {

  protected WebDriver driver;
  protected UIGuiceScoped guiceScoped;
  protected Actions actions;
  protected Waiters waiters;

  @Inject
  public AbsPageObject(UIGuiceScoped scenarioScoped) {
    this.guiceScoped = scenarioScoped;
    this.driver = scenarioScoped.driver;
    this.actions = new Actions(scenarioScoped.driver);
    this.waiters = new Waiters(driver);

    PageFactory.initElements(driver, this);
  }

  public WebElement $(By locator) {
    return driver.findElement(locator);
  }
  public WebElement $(String locator) {
    return driver.findElement(locatorAnalizator(locator));
  }
  public List<WebElement> $$(By locator) {
    return driver.findElements(locator);
  }
  public List<WebElement> $$(String locator) {
    return driver.findElements(locatorAnalizator(locator));
  }

  private By locatorAnalizator(String locator) {
    if (locator.startsWith("/")) {
      return By.xpath(locator);
    } else {
      return By.cssSelector(locator);
    }
  }
}
