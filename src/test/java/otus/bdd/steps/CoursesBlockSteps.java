package otus.bdd.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import otus.bdd.components.CoursesTilesComponent;

public class CoursesBlockSteps {

    @Inject
    private CoursesTilesComponent coursesTilesComponent;

    @Если("Кликнуть на плитку курса {string}")
    public void clickSelectedCourseTile(String title) {
        coursesTilesComponent.moveCourse(title);
    }


    @Если("Кликнуть на плитку курса стартующего раньше всех")
    public void clickEarlyCourseTile() {
        coursesTilesComponent.getEarlierLaterCourse(true);
    }
}
