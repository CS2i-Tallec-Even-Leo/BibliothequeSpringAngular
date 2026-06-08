# =========================
# BUILD STAGE
# =========================
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# Copy Maven wrapper + project files
COPY . .

# Ensure mvnw is executable
RUN chmod +x mvnw

# Build the application (skip tests for CI speed)
RUN ./mvnw clean package -DskipTests

# =========================
# RUNTIME STAGE
# =========================
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy built JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]