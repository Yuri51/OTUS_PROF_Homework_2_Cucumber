package otus.bdd.pages;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import otus.bdd.pageobject.AbsPageObject;
import otus.bdd.annotations.Path;
import otus.bdd.annotations.Template;
import otus.bdd.annotations.UrlTemplates;
import otus.bdd.support.UIGuiceScoped;

public abstract class AbsBasePage<T> extends AbsPageObject<T> {
  @Inject
  public AbsBasePage(UIGuiceScoped scenarioScoped) {
    super(scenarioScoped);
  }

  private static final String BASE_URL = System.getProperty("base.url");
  @FindBy(xpath = "//h2[text()='Авторские онлайн‑курсы для профессионалов']")
  private WebElement header;

  public T pageHeaderShouldBeVisible() {
    assertThat(waiters.waitForElementVisible(header))
        .as("Header should be visible")
        .isTrue();
    return (T) this;
  }

  public T pageHeaderShouldBeSameAs(String header) {
    assertThat(this.header.getText())
        .as("header should be {}", header)
        .isEqualTo(header);
    return (T) this;
  }

  private String getPath() {
    Class<? extends AbsBasePage> clazz = this.getClass();
    if (clazz.isAnnotationPresent(Path.class)) {
      Path path = clazz.getAnnotation(Path.class);
      return path.value();
    }
    return "";
  }

  public T open() {
    String path = getPath();
    String url = "";
    url = BASE_URL + path;
    driver.get(url);
    return (T) this;
  }

  public T open(String name, String[] params) {
    Class<? extends AbsBasePage> clazz = this.getClass();
    if (clazz.isAnnotationPresent(UrlTemplates.class)) {
      UrlTemplates urlTemplates = clazz.getAnnotation(UrlTemplates.class);
      Template[] templates = urlTemplates.templates();

      for (Template template : templates) {
        if (template.name().equals(name)) {
          String urlTemplate = template.template();
          for (int i = 0; i < params.length; i++) {
            urlTemplate = urlTemplate.replace(String.format("{%d}", i + 1), params[i]);
          }
          driver.get(BASE_URL + urlTemplate);
        }
      }
    }
    return (T) this;
  }

}