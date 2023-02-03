FROM openjdk:11
ADD ./target/app-0.0.1-SNAPSHOT.jar /root/app-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar","app-0.0.1-SNAPSHOT.jar"]