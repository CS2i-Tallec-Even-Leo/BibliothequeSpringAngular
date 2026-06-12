# =========================
# BUILD BACKEND
# =========================
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copy project files
COPY . .

RUN ls -la
# Ensure wrapper is executable (safe for CI)
RUN chmod +x Bibliotheque-Back/Bibliotheque/mvnw
# Build application
RUN ./Bibliotheque-Back/Bibliotheque/mvnw clean package

# =========================
# RUNTIME
# =========================
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy built jar
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]