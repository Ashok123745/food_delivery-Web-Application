# Stage 1: Compile all Java servlets with Tomcat 10.1 (Jakarta EE) dependencies
FROM tomcat:10.1-jdk17-temurin AS builder

WORKDIR /build

# Copy all project files
COPY . .

# Compile Java source files using Tomcat 10.1's Jakarta libraries
RUN mkdir -p /build/classes && \
    find src -name "*.java" > /build/sources.txt && \
    javac -cp "/usr/local/tomcat/lib/*:src/main/webapp/WEB-INF/lib/*:WebContent/WEB-INF/lib/*:." \
          -d /build/classes @/build/sources.txt

# Stage 2: Final Tomcat 10.1 Runtime Image
FROM tomcat:10.1-jdk17-temurin

# Disable Tomcat shutdown port to prevent Render health-check conflicts
RUN sed -i 's/port="8005"/port="-1"/g' /usr/local/tomcat/conf/server.xml

# Copy web files (HTML, JSP, web.xml)
COPY ./src/main/webapp/ /usr/local/tomcat/webapps/ROOT/

# Copy the compiled classes into WEB-INF/classes
COPY --from=builder /build/classes/ /usr/local/tomcat/webapps/ROOT/WEB-INF/classes/

# Copy any third-party JARs (MySQL driver, etc.)
COPY ./src/main/webapp/WEB-INF/lib/ /usr/local/tomcat/webapps/ROOT/WEB-INF/lib/

EXPOSE 8080
ENV PORT=8080

CMD ["catalina.sh", "run"]