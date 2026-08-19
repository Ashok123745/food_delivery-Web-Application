FROM tomcat:9.0-jdk17-openjdk-slim
COPY ./build/classes /usr/local/tomcat/webapps/ROOT/WEB-INF/classes
COPY ./WebContent /usr/local/tomcat/webapps/ROOT/
EXPOSE 8080
CMD ["catalina.sh", "run"]