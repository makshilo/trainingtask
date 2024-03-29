# Тренировочный проект: Управление задачами

## Требования
Минимально необходимые инструменты для сборки и запуска приложения:

* jdk-11

<!-- Startup -->
## Запуск
Внутри корневой папки проекта в терминале или командной строке, необходимо выполнить следующие команды:

  ```sh
  gradlew startDatabase
  ```

  ```sh
  gradlew startServer
  ```
Также при первом запуске после запуска се необходимо выполнить [команду создания базы данных](#create-database)


<!-- Usage -->
## Использование
Ресурc доступен по url:

<localhost:8089>

<!-- Stop -->
## Остановка
Остановка сервера производится прерыванием команды старта сервера сочетанием клавиш CTRL+C в окне командной строки.
После остановки сервера приложения нужно остановить сервер базы данных следующей командой:

  ```sh
  gradlew stopDatabase
  ```

<!-- Database management -->
## Управление базой данных

#### Команда запуска сервера базы данных:
  ```sh
  gradlew startDatabase
  ```

#### Команда остановки сервера базы данных:
  ```sh
  gradlew stopDatabase
  ```

#### <a id="create-database"></a> Команда создания базы данных:
  ```sh
  gradlew createDatabase
  ```

#### Команда удаления базы данных:
  ```sh
  gradlew dropDatabase
  ```

<!-- Config -->
## Конфигурация

**Конфигурация приложения** и **базы данных** находятся в файле ```gradle.properties``` 
по пути: ```com.qulix.shilomy.trainingtask.web/gradle.properties```