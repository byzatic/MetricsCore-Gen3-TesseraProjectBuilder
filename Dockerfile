# Step 1: Use Maven image to build the application
FROM docker.io/maven:3.8.6-eclipse-temurin-17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and download dependencies (cache this step if possible)
COPY pom.xml .

## Download project dependencies (this step will be cached until pom.xml changes)
#RUN mvn dependency:go-offline

# Copy the entire project source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests -U --batch-mode package




# Step 2: Use a JRE image to run the application
FROM --platform=linux/amd64 docker.io/openjdk:17-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/metricscore-gen3-tessera-project-builder-*-SNAPSHOT-jar-with-dependencies.jar /app/app.jar

# Copy the Build script and give the right
COPY build.sh /usr/local/bin/build
RUN chmod +x /usr/local/bin/build


