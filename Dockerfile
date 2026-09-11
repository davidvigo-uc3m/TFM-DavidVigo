# 1. Imagen base oficial con Java 17
FROM eclipse-temurin:17-jre-alpine

# 2. Directorio de trabajo dentro del contenedor
WORKDIR /app

# 3. Copiamos el ejecutable generado en target/ hacia el contenedor
COPY target/*.war app.jar

# 4. Exponemos el puerto 8080 en el que escucha Spring Boot
EXPOSE 8080

# 5. Comando para arrancar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]