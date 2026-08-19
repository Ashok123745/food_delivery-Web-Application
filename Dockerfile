FROM tomcat:9-jdk17-temurin

# Disable Tomcat shutdown port
RUN sed -i 's/port="8005"/port="-1"/g' /usr/local/tomcat/conf/server.xml

# 1. Copy web assets (HTML, JSP, web.xml)
COPY ./src/main/webapp /usr/local/tomcat/webapps/ROOT/

# 2. Copy compiled Java servlet classes
COPY ./build/classes/ /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/

# 3. Copy any third-party JARs (MySQL driver, JSTL, etc.)
COPY ./src/main/webapp/WEB-INF/lib/ /usr/local/tomcat/webapps/ROOT/WEB-INF/lib/

EXPOSE 8080
ENV PORT=8080

CMD ["catalina.sh", "run"]