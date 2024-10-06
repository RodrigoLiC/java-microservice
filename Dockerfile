# Usamos la imagen oficial de Maven con OpenJDK 17 en su versión "slim"
FROM maven:3.8.4-openjdk-17-slim

# Establecer el directorio de trabajo en el contenedor para construir la aplicación
WORKDIR /app

# Copiar pom.xml y descargar dependencias
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copiar el codigo fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests -X

# Exponer el puerto 8010
EXPOSE 8011

ENV APP_NAME=cloudapi \
    APP_PORT=8011 \
    DB_HOST=localhost \
    DB_PORT=5432 \
    DB_NAME=cloud \
    DB_USER=postgres \
    DB_PASS=utec \
    PYTHON_API_URL=http://127.0.0.1:8000/cursos/

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "target/cloudapi-0.0.2.jar"]