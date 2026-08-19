FROM tomcat:9-jdk17-temurin

# Disable Tomcat shutdown port to stop Render port-scanning conflicts
RUN sed -i 's/port="8005"/port="-1"/g' /usr/local/tomcat/conf/server.xml

# Copy compiled classes into Tomcat
COPY ./build/classes /usr/local/tomcat/webapps/ROOT/WEB-INF/classes

# Copy web files into Tomcat ROOT
COPY ./src/main/webapp /usr/local/tomcat/webapps/ROOT/

EXPOSE 8080
ENV PORT=8080

CMD ["catalina.sh", "run"]