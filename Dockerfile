FROM openjdk:17-alpine
WORKDIR /app
COPY . ./
RUN ./mvnw -DoutputFile=target/mvn-dependency-list.log -B -DskipTests clean dependency:list install
CMD ["sh", "-c", "java -jar target/*.jar"]