package otus.bdd.components.popups;

import com.google.inject.Inject;
import otus.bdd.annotations.Popup;
import otus.bdd.exceptions.PathEmptyException;
import otus.bdd.pageobject.AbsPageObject;
import otus.bdd.support.UIGuiceScoped;

public abstract class AbsPopup<T> extends AbsPageObject<T> implements IPopup<T> {
  @Inject
  public AbsPopup(UIGuiceScoped scenarioScoped) {
    super(scenarioScoped);
  }

  private String getPopup() throws PathEmptyException {
    Class<? extends AbsPopup> clazz = this.getClass();
    if (clazz.isAnnotationPresent(Popup.class)) {
      Popup popup = clazz.getAnnotation(Popup.class);
      return popup.value();
    }
    throw new PathEmptyException();
  }
}


