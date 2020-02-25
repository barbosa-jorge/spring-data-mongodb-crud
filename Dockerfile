FROM maven:3-jdk-8-openj9

EXPOSE 8082

WORKDIR /tmp/mongo-app

CMD ["mvn","spring-boot:run"]
