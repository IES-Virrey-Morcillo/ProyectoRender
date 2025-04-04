# Usa una imagen ligera de Java 17
FROM eclipse-temurin:17-jdk-alpine

# Carpeta donde se ejecutará tu app
WORKDIR /app

# Copia el jar generado por Maven
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# Render usa esta variable de entorno para el puerto
ENV PORT=8080

# Expone el puerto
EXPOSE 8080

# Comando para arrancar tu app
ENTRYPOINT ["java", "-jar", "app.jar"]
