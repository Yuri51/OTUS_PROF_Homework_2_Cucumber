package otus.bdd.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import otus.bdd.pages.PreparingCoursePage;

public class PreparingCoursePageSteps {

  @Inject
  private PreparingCoursePage preparingCoursePage;

  @Если("Открыта страница подготовительных курсов")
  public void openPage() {
    preparingCoursePage.open();
  }

  @Тогда("Выведена в консоль информация о самом {string} курсе")
  public void selectMostOrLeastExpensiveCourse(String type) {
    preparingCoursePage.printTheMostExpensiveOrCheapestCourse(type);
  }

}
