# syntax=docker/dockerfile:1

# ---------- Stage 1: build ----------
FROM eclipse-temurin:21-jdk AS build
WORKDIR /workspace

# Wrapper y POMs
COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml ./
COPY orders-api/pom.xml orders-api/
COPY notifications-lambda/pom.xml notifications-lambda/
RUN chmod +x mvnw

COPY orders-api/src orders-api/src

# se empaqueta orders-api
RUN --mount=type=cache,target=/root/.m2 \
    ./mvnw -B -q -pl orders-api -am package -DskipTests \
 && cp orders-api/target/orders-api-*.jar /workspace/app.jar

# ---------- Stage 2: runtime ----------
FROM eclipse-temurin:21-jre
WORKDIR /app

RUN groupadd --system app && useradd --system --gid app --no-create-home app

COPY --from=build --chown=app:app /workspace/app.jar app.jar

USER app
EXPOSE 8080

ENTRYPOINT ["java", "-XX:MaxRAMPercentage=75", "-jar", "/app/app.jar"]
