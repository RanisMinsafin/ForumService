Простой движок форума/доски объявлений (Backend)
================================================

Этот проект представляет собой простой движок форума, реализованный на языке Java с использованием фреймворка Spring
Boot. Он обеспечивает хранение данных в PostgreSQL и предоставляет REST API для выполнения CRUD операций с топиками и
сообщениями в них.

Функциональность
----------------

* Получение списка топиков
* Получение сообщений в указанном топике
* Создание топика с первым сообщением
* Создание сообщения в указанном топике
* Редактирование сообщения
* Удаление сообщения

Дополнительная функциональность
---------------- 
* Пагинация для топиков и сообщений
* Аутентификация пользователей
* REST API администратора для редактирования и удаления сообщений и топиков

Технологии
----------

* Java
* Spring Boot
* JSON Web Token (JWT) 
* Swagger
* Maven (для автоматизации сборки)
* PostgreSQL
* Docker
Использование
-------------

* Регистрация пользователя: POST /auth/sign-up
* Авторизация пользователя: POST /auth/sign-in
* Получение списка топиков: GET /topics
* Получение сообщений в топике: GET /topics/{id}
* Создание нового топика: POST /topics
* Создание нового сообщения: POST /topics/{topicId}/messages
* Редактирование сообщения: PUT /messages/{id}
* Удаление сообщения: DELETE /messages/{id}
* Получение роли ADMIN: GET /get-admin
* Изменение темы: PUT /admin/topics/{id}
* Удаление темы: DELETE /admin/topics/{id}
* Изменение сообщения: PUT /admin/messages/{id}
* Удаление сообщения: DELETE /admin/messages/{id}

Запуск
---------

1. Склонируйте репозиторий:

       git clone <ссылка на репозиторий>

2. Перейдите в директорию ForumService:

       cd ForumService

3. Запустите приложение с помощью Docker Compose:

        docker compose up
