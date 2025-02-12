# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the project files to the container
COPY . .

# Give execute permission to the mvnw script
RUN chmod +x ./mvnw

# Build the application using Maven
RUN ./mvnw clean package -DskipTests

# Expose the application port
EXPOSE 3060

# Run the application
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
