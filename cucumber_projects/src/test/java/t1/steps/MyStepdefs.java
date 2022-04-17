package t1.steps;

import io.cucumber.java.ru.Дано;
import t1.pages.YandexMainPage;
import t1.pages.YandexWeatherPage;

public class MyStepdefs {
    @Дано("Получить прогноз погоды на {string}")
    public void testApplicationIsStarted(String day) {
        if (day.equalsIgnoreCase("сегодня")){
            YandexMainPage yandexMainPage = new YandexMainPage();
            yandexMainPage.getCurrentTemp();
        }
        else {
            YandexWeatherPage yandexWeatherPage = new YandexWeatherPage();
            yandexWeatherPage.getNextDayTemp(day);
        }

    }

    @Дано("Перейти на страницу {string}")
    public void goToPage(String page){
        YandexMainPage yandexMainPage = new YandexMainPage();
        yandexMainPage.goToPage(page);
    }
}
