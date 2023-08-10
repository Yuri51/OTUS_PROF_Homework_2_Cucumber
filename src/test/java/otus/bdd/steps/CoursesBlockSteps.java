package otus.bdd.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import otus.bdd.components.CoursesTilesComponent;
import java.text.ParseException;

public class CoursesBlockSteps {

  @Inject
  private CoursesTilesComponent coursesTilesComponent;

  @Если("Кликнуть на плитку курса {string}")
  public void clickSelectedCourseTile(String title) {
    coursesTilesComponent.moveCourse(title);
  }

  @Тогда("Выведена в консоль информация по курсу стартующего на дату {string} или позже")
  public void printCourse(String date) throws ParseException {
    coursesTilesComponent.printCourseNameAndDate(date);
  }
}
