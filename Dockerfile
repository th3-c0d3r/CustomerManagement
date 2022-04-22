FROM adoptopenjdk/openjdk11-openj9:alpine
ADD target/Assignment_Customer_SpringBootMySQL-0.0.1-SNAPSHOT.jar /hariapp/SpringBootDemo1-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT java -jar /hariapp/SpringBootDemo1-0.0.1-SNAPSHOT.jar
