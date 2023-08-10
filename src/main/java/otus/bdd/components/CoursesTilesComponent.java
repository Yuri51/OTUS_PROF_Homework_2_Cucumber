package otus.bdd.components;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import otus.bdd.annotations.Component;
import otus.bdd.support.UIGuiceScoped;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;

@Component("id:Course_block")
public class CoursesTilesComponent extends AbsComponent<CoursesTilesComponent> {
  @Inject
  public CoursesTilesComponent(UIGuiceScoped scenarioScoped) {
    super(scenarioScoped);
  }

  private String coursesNamesInSectionSelector = "//body//following::div[./h5][%d]";
  private String courseNameSelector = "div>picture>img[alt='%s']";
  private String courseStartDateOneLocator = "//body//following::span[contains(text(), 'С ')][%d]";
  private String courseStartDateTwoLocator = "//body//following::span[contains(text(), 'В ')][%d]";
  private String courseTrainingPeriodLocator = "//span[contains(text(), 'месяц')]";
  private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMM", Locale.forLanguageTag("ru"));
  @FindBy(xpath = "//a[div/div/picture/img]")
  private List<WebElement> courseTile;

  public List<WebElement> getCourseNames() {
    return List.copyOf(courseTile);
  }

  public void moveCourse(String title) {
    moveAndPerform(guiceScoped.driver.findElement(By.cssSelector(String.format(courseNameSelector, title))));
  }

  private Map<String, Date> getNameDataCourseList(List<WebElement> courseTile) {
    Map<String, Date> foundCourse = new HashMap<>();
    for (int i = 1; i < courseTile.size(); i++) {
      String courseName = driver.findElement(By.xpath(String.format(coursesNamesInSectionSelector, i))).getText();
      WebElement elements1 = driver.findElement(By.xpath(String.format(courseStartDateOneLocator, i)));
      if (elements1 != null) {
        Date date = parseDate(elements1.getText().replace("С ", ""));
        foundCourse.put(courseName, date);
      }
    }
    return foundCourse;
  }

  private Date parseDate(String text) {
    try {
      return simpleDateFormat.parse(text);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

  public void clickCourseByDate(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    String partLinkText = calendar.get(Calendar.DAY_OF_MONTH)
        + " " + Month.of(calendar.get(Calendar.MONTH) + 1).getDisplayName(TextStyle.FULL, new Locale("ru"));
    this.actions.moveToElement(findElement(By.partialLinkText(partLinkText)))
        .click()
        .build()
        .perform();
  }

  private WebElement findElement(By by) {
    return driver.findElement(by);
  }

  public void printCourseNameAndDate(String dateStart) throws ParseException {
    List<WebElement> courseTile = getCourseNames();
    Map<String, Date> courseNamesAndDates = getNameDataCourseList(courseTile);
    Date date = parseDate(dateStart);
    for (Map.Entry<String, Date> entry : courseNamesAndDates.entrySet()) {
      if (entry.getValue().equals(date) || entry.getValue().after(date)) {
        System.out.println("Название курса = " + entry.getKey() + "  Дата старта = " + simpleDateFormat.format(entry.getValue()));
      }
    }
  }
}







