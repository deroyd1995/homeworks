package t1.steps;

import io.cucumber.java.ru.Дано;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import t1.pages.YandexMainPage;
import t1.pages.YandexWeatherPage;

public class MyStepdefs {

    private static final Logger logger = LoggerFactory.getLogger(MyStepdefs.class);


    @Дано("Получить прогноз погоды на {string}")
    public void getWeather(String day) {
        if (day.equalsIgnoreCase("сегодня")){
            YandexMainPage yandexMainPage = new YandexMainPage();
            yandexMainPage.getCurrentTemp();
        }
        else {
            YandexMainPage yandexMainPage = new YandexMainPage();
            yandexMainPage.goToPage("Погода");
            YandexWeatherPage yandexWeatherPage = new YandexWeatherPage();
            yandexWeatherPage.getNextDayTemp(day);
        }
        logger.info("Запрошена погода на "+day);
    }

    @Дано("Перейти на страницу {string}")
    public void goToPage(String page){
        YandexMainPage yandexMainPage = new YandexMainPage();
        yandexMainPage.goToPage(page);
        logger.info("Осуществлён переход на страницу '"+page+"'");
    }

    @Дано("Выполняем запрос по теме {string}")
    public void searchTheme(String theme){
        YandexMainPage yandexMainPage = new YandexMainPage();
        yandexMainPage.searchInYandex(theme);
        logger.info("Осуществлён поиск по теме '"+theme+"'");
    }
}
