FROM openjdk:17-oracle
EXPOSE 9090
ADD target/HrWebApplication.war HrWebApplication.jar
ENTRYPOINT ["java","-jar","/HrWebApplication.jar"]