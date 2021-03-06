#language: ru
Функционал: Ходим по главной странице Amazon

  @001
  @all_cases
  Сценарий: Кейс выполнения запроса по заданной теме
    * пользователь на странице "Главная страница Amazon" кликает на элемент "Главное меню"
    * пользователь на странице "Главное меню Amazon" проверяет, что элемент "Раздел электроники" активен
    * пользователь на странице "Главное меню Amazon" кликает на элемент "Раздел электроники"
    * пользователь на странице "Главное меню Amazon" ожидает элемент "Результат поиска: электроника"
    * пользователь на странице "Главное меню Amazon" кликает на элемент "Наушники"
    * пользователь на странице "Результаты поиска Amazon" ожидает элемент "Поиск по разделу:"
    * пользователь на странице "Результаты поиска Amazon" сохраняет текст элемента "Поиск по разделу:" в переменную контекста "текст поиск по:"
    * сравнить переменную из контекста: "текст поиск по:" с ожидаемым результатом: "Audio Headphones"
    * пользователь на странице "Результаты поиска Amazon" находит элемент "Samsung" в коллекции "Коллекция производителей", общий локатор "Один из брендов" и "кликает"
    * пользователь на странице "Результаты поиска Amazon" проверяет, что все элементы из одной коллекции "Коллекция предметов" принадлежат одному бренду "Samsung" и имеют общий локатор "Предмет из коллекции"

