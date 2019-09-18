FROM open-liberty:kernel

COPY target/demo-project.war /config/apps
COPY src/main/liberty/config/server.xml /config/server.xml

RUN configure.sh > /dev/null 2>&1
