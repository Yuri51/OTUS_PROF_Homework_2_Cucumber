package otus.bdd.steps;

import com.google.inject.Inject;
import io.cucumber.java.ru.Пусть;
import otus.bdd.exceptions.BrowserNotSupportedException;
import otus.bdd.factory.FactoryDriver;
import otus.bdd.support.UIGuiceScoped;

public class CommonSteps {

    @Inject
    private FactoryDriver factoryDriver;
    @Inject
    private UIGuiceScoped guiceScoped;

    @Пусть("Открыт браузер {string}")
    public void openBrowser(String browserName ) throws BrowserNotSupportedException {
        guiceScoped.driver = factoryDriver.getDriver(browserName);
    }
}