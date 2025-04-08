1. Выполняем все пять шагов из документации https://www.jenkins.io/doc/book/installing/docker/
   (На четвертом шаге команда build выполняется в той же директории, что и лежит созданный вами Dockerfile)
2. Далее переходим на localhost:8080 и видим запрос на ввода пароля администратора
3. Идем в терминал
4. Пишем docker ps и находим айди контейнера jenkins
5. docker container exec -it d22f571c4066 cat /var/jenkins_home/secrets/initialAdminPassword
6. Получаем пароль, вставляем на сайте в форму
7. Согласиться на установку рекомендованных плагинов