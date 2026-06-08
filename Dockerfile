# =========================
# BUILD BACKEND
# =========================
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Build context IS Bibliotheque-Back/Bibliotheque/, so just copy "."
COPY . .

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests


# =========================
# RUNTIME
# =========================
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]