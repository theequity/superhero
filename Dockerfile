FROM openjdk:11
WORKDIR /app
COPY "target/superhero-0.0.1-SNAPSHOT.jar" "app.jar" 
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]