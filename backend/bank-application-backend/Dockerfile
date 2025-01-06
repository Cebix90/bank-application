# Używamy obrazu Javy 21 (wymagany dla Spring Boot)
FROM eclipse-temurin:21-jdk-alpine

# Ustawiamy katalog roboczy w kontenerze
WORKDIR /app

# Kopiujemy plik JAR, który zostanie wygenerowany przez Maven
COPY target/bank-application-backend-0.0.1-SNAPSHOT.jar app.jar

# Ekspozycja portu na backend (np. 8080)
EXPOSE 8080

# Komenda startowa do uruchomienia aplikacji
ENTRYPOINT ["java", "-jar", "app.jar"]