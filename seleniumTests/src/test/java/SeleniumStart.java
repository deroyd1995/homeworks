import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class SeleniumStart {
    @BeforeEach
    public void setUp(){
        //подключение к БД
        //авторизация
        //открытие браузера
        System.out.println("completed @BeforeEach");
    }

    @AfterEach
    public void teadDown(){
        System.out.println("completing @AfterEach");
        //отключение от БД
        //закрытие сессии
        //закрытие браузера
        //получение скриншота
    }
}
