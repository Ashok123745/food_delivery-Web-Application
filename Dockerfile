FROM tomcat:9.0-jdk17-openjdk-slim

# Copy compiled classes into Tomcat's classes directory
COPY ./build/classes /usr/local/tomcat/webapps/ROOT/WEB-INF/classes

# Copy web files from src (where webapp/JSP files live)
COPY ./src/main/webapp /usr/local/tomcat/webapps/ROOT/

EXPOSE 8080
CMD ["catalina.sh", "run"]