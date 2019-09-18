FROM open-liberty:kernel

COPY target/demo-project.war /config/apps
COPY src/main/liberty/config/ /config/

RUN configure.sh > /dev/null 2> /dev/null