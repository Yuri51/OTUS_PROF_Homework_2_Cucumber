package otus.bdd.pages;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import java.util.List;
import otus.bdd.annotations.Path;
import otus.bdd.support.UIGuiceScoped;

@Path("/")
public class MainPage extends AbsBasePage<MainPage> {
  @Inject
  public MainPage(UIGuiceScoped scenarioScoped) {
    super(scenarioScoped);
  }

  private String courseSectionsLocator = "//main//div[section]";

  public MainPage pageCourseSectionsShouldBeVisible() {
    List<WebElement> sections = $$(courseSectionsLocator);
    long actualCourseThumbs = sections.stream().filter(
        (WebElement courseThumbs) -> waiters.waitForElementVisible(courseThumbs)).count();
    assertThat(actualCourseThumbs)
        .as("")
        .isEqualTo(sections.size());
    return this;
  }
}

