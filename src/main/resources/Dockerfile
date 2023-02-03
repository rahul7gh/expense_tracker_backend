FROM openjdk:11
ADD ${project.build.finalName}.jar ${project.build.finalName}.jar
EXPOSE $PORT
CMD ["java", "-jar","${project.build.finalName}.jar"]