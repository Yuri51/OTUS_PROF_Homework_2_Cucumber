package otus.bdd.pages;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.as;

import com.google.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import otus.bdd.annotations.Path;
import otus.bdd.support.UIGuiceScoped;

@Path("/")
public class CoursePage extends AbsBasePage<CoursePage> {

  @Inject
  public CoursePage(UIGuiceScoped scenarioScoped) { super(scenarioScoped); }

  private String courseNameHeaderLocator = "//*[contains(@class, 'course-header2__title')][contains (text(), '%s')]";

  public void coursePageIsOpened(String title) {
    Assertions.assertEquals((guiceScoped.driver.findElement(By.xpath(String.format(courseNameHeaderLocator, title))).getText()), title);
  }
}

