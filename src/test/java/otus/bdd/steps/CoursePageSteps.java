package otus.bdd.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Тогда;
import otus.bdd.pages.CoursePage;

public class CoursePageSteps {
    @Inject
    private CoursePage coursePage;

    @Тогда("Откроется страница с названием курса {string}")
    public void pageCourseShouldBeSameAs(String title) {
        coursePage.coursePageIsOpened(title);
    }

//    @Тогда("Откроется страница курса")
//    public void pageCourseIsOpened()
//    {
//        coursePage.coursePageIsOpened();
//    }
}
