FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar el JAR construido al contenedor
COPY ./target/tarea.victor-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto 8001
EXPOSE 8001

CMD ["java", "-jar", "app.jar"]