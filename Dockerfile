# Указываем базовый образ, содержащий JDK
FROM openjdk:17-jdk-alpine

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файл jar в рабочую директорию
COPY target/chat-application.jar /app/chat-application.jar

# Устанавливаем порт, который будет слушать приложение
EXPOSE 8080

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "chat-application.jar"]
