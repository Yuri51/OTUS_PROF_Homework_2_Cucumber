package otus.bdd.components;

import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import otus.bdd.pageobject.AbsPageObject;
import otus.bdd.support.UIGuiceScoped;

public class AbsComponent<T> extends AbsPageObject<T> {

  @Inject
  public AbsComponent(UIGuiceScoped scenarioScoped) {
    super(scenarioScoped);
    this.actions = new Actions(driver);
  }

  protected WebElement getComponentEntity() {
    return null;
  }

  protected void moveAndPerform(WebElement element) {
    waiters.waitForCondition(ExpectedConditions.visibilityOf(element));
    actions.moveToElement(element);
    actions.click().build().perform();

  }
}


