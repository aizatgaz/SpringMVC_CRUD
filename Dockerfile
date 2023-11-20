FROM tomcat:10.0.27-jdk17-temurin

COPY ./target/tasks.war /usr/local/tomcat/webapps/