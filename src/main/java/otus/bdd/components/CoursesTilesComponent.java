package otus.bdd.components;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import otus.bdd.annotations.Component;
import org.openqa.selenium.By;
import otus.bdd.support.UIGuiceScoped;


@Component("id:Course_block")
public class CoursesTilesComponent extends AbsComponent<CoursesTilesComponent> {
  @Inject
  public CoursesTilesComponent(UIGuiceScoped scenarioScoped) {
    super(scenarioScoped);
  }

  private String coursesNamesInSectionSelector = "div>h5";
  private String courseNameSelector = "div>picture>img[alt='%s']";
  private String courseStartDateLocator = "//span[contains(text(), 'С ')]";
  private String courseTrainingPeriodLocator = "//span[contains(text(), 'месяц')]";

  public List<WebElement> getCourseNames() {
    return driver.findElements(By.cssSelector(coursesNamesInSectionSelector));
  }

  public void moveCourse(String title) {
    moveAndPerform(guiceScoped.driver.findElement(By.cssSelector(String.format(courseNameSelector, title))));
  }

  public CoursesTilesComponent filterCoursesByTitle(String title) {
    getCourseNames();
    List<WebElement> collect = $$(coursesNamesInSectionSelector);
    List<WebElement> actualCourse = collect.stream()
        .filter(element -> element.getText().equals(title))
        .collect(Collectors.toList());
    assertThat(actualCourse)
        .as("Course '%s' not found", title)
        .hasSize(1);
    return this;
  }

  private List<Date> getDataList() {
    SimpleDateFormat format = new SimpleDateFormat("d MMMM", Locale.forLanguageTag("ru"));
    List<Date> dataList = driver.findElements(By.xpath(String.format(courseStartDateLocator)))
        .stream()
        .map(element -> element.getText().replace("С ", ""))
        .map(date -> {
          try {
            return format.parse(date);
          } catch (ParseException e) {
            throw new RuntimeException(e);
          }
        })
        .collect(Collectors.toList());
    return dataList;
  }

  public CoursesTilesComponent getEarlierLaterCourse(boolean isEarly) {
    BinaryOperator<Date> selectDate = null;
    if (isEarly) {
      selectDate = (Date date1, Date date2) -> date1.before(date2) ? date1 : date2;
    } else {
      selectDate = (Date date1, Date date2) -> date1.after(date2) ? date1 : date2;
    }
    Date earlyDate = getDataList()
        .stream()
        .reduce(selectDate)
        .get();
    clickCourseByDate(earlyDate);
    return this;
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
}


