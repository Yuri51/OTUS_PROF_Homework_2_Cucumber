package otus.bdd.components;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import otus.bdd.support.UIGuiceScoped;

public class EducationMenuComponent extends AbsComponent<EducationMenuComponent> {
  @Inject
  public EducationMenuComponent(UIGuiceScoped scenarioScoped) {
    super(scenarioScoped);
  }

  private String educationMenu = "//div[span[@title='%s']]";
  private String educationMenuDownLocator = educationMenu + "//div[div/p[text()='Все курсы']]";

  public EducationMenuComponent moveToEducationMenu(String title) {
    WebElement ele = driver.findElement(By.xpath(String.format(educationMenu, title)));
    Actions action = new Actions(driver);
    action.moveToElement(ele).build().perform();
    return this;
  }

}
