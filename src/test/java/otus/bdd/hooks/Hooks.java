package otus.bdd.hooks;

import com.google.inject.Inject;
import io.cucumber.java.After;
import otus.bdd.support.UIGuiceScoped;

public class Hooks {
  @Inject
  private UIGuiceScoped uiGuiceScoped;

  @After
  public void close() {
    if (uiGuiceScoped.driver != null) {
      uiGuiceScoped.driver.close();
      uiGuiceScoped.driver.quit();
    }

  }
}
