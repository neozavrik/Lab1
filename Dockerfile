FROM openjdk:21-jdk
LABEL authors="ka9mal6t"

# Встановлюємо робочу директорію
WORKDIR /app

# Копіюємо ваш JAR файл у контейнер
COPY build/libs/demo-0.0.1-SNAPSHOT.jar app.jar

# Вказуємо порт, на якому буде працювати додаток
EXPOSE 8080

# Запуск додатку
ENTRYPOINT ["java", "-jar", "app.jar"]
