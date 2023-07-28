package otus.bdd.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import otus.bdd.pages.MainPage;

public class MainPageSteps {
    @Inject
    private MainPage mainPage;
    @Пусть("Открыта главная страница")
    public void openMainPage() {
        mainPage.open();

    }
}
