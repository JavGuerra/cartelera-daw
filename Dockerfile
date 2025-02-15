FROM openjdk:17-alpine
WORKDIR /app
COPY . ./
RUN chmod +x mvnw && ./mvnw -DoutputFile=target/mvn-dependency-list.log -B -DskipTests clean dependency:list install
CMD ["sh", "-c", "java -jar target/*.jar"]