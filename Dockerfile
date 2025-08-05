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

COPY tessera_modules ./tessera_modules

RUN mkdir -p ./src/main/resources/services ./src/main/resources/shared ./src/main/resources/workflow_routines
RUN cp ./tessera_modules/service-prometheus-export-0.0.1-jar-with-dependencies.jar ./src/main/resources/services
RUN cp ./tessera_modules/sharedresources-project-common-0.0.1-jar-with-dependencies.jar ./src/main/resources/shared
RUN cp ./tessera_modules/workflowroutine-data-enrichment-0.0.1-jar-with-dependencies.jar ./src/main/resources/workflow_routines
RUN cp ./tessera_modules/workflowroutine-graph-lifting-data-0.0.1-jar-with-dependencies.jar ./src/main/resources/workflow_routines
RUN cp ./tessera_modules/workflowroutine-processing-status-0.0.1-jar-with-dependencies.jar ./src/main/resources/workflow_routines
RUN cp ./tessera_modules/workflowroutine-prometheus-get-data-0.0.1-jar-with-dependencies.jar ./src/main/resources/workflow_routines

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


