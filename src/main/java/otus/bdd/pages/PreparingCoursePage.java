package otus.bdd.pages;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import otus.bdd.annotations.Path;
import otus.bdd.support.UIGuiceScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("online")

public class PreparingCoursePage extends AbsBasePage<PreparingCoursePage> {

  @Inject
  public PreparingCoursePage(UIGuiceScoped scenarioScoped) {
    super(scenarioScoped);
  }

  @FindBy(css = ".lessons__new-item-container")
  private List<WebElement> coursesTile;

  private String courseNameSelector = ".lessons__new-item-title";
  private String coursePriceSelector = "div.lessons__new-item-price";

  public List<WebElement> getCourseItems() {
    return List.copyOf(coursesTile);
  }

  public void printTheMostExpensiveOrCheapestCourse(String type) {
    List<WebElement> courseTile = getCourseItems();
    Map<String, Integer> courseNamesAndDates = getCourseTitlesAndPrices(courseTile);
    Map.Entry<String, Integer> namePriceEntry = null;
    if (type.equals("дешёвый")) {
      namePriceEntry = courseNamesAndDates.entrySet()
          .stream()
          .min(Map.Entry.comparingByValue())
          .get();
    } else {
      namePriceEntry = courseNamesAndDates.entrySet()
          .stream()
          .max(Map.Entry.comparingByValue())
          .get();
    }
    System.out.println("Название курса - " + namePriceEntry.getKey() + " / Стоимость - " + namePriceEntry.getValue());
  }

  public Map<String, Integer> getCourseTitlesAndPrices(List<WebElement> courseItems) {
    Map<String, Integer> result = new HashMap<>();
    for (WebElement webElement : courseItems) {
      String courseTitle = webElement.findElement(By.cssSelector(courseNameSelector)).getText();
      Integer price = Integer.valueOf(webElement.findElement(By.cssSelector(coursePriceSelector)).getText().replace(" ₽", ""));
      result.put(courseTitle, price);
    }
    return result;
  }
}
