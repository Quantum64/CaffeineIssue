FROM openjdk:16
COPY ./source .
RUN chmod +x ./gradlew
RUN ./gradlew shadowJar
CMD ["java", "-jar", "/build/libs/caffeine-issue-all.jar"]
