FROM ubuntu:14.04.2
 
ENV DEBIAN_FRONTEND noninteractive
ENV JAVA_HOME /opt/jdk/jre
ENV PATH $PATH:/opt/jdk/jre/bin
 
RUN apt-get update && apt-get install -y wget
 
RUN wget --no-check-certificate -O /tmp/pkg.tar.gz --header "Cookie: oraclelicense=a" http://download.oracle.com/otn-pub/java/jdk/7u65-b17/jdk-7u65-linux-x64.tar.gz && \
    tar -zxf /tmp/pkg.tar.gz --xform='s/[^\/]*/jdk/' -C /opt && \
    rm -rf /tmp/*

WORKDIR .
EXPOSE 8080
ADD target/service-java-0.0.1-SNAPSHOT.jar /data/service-java-0.0.1-SNAPSHOT.jar
CMD java -jar /data/service-java-0.0.1-SNAPSHOT.jar
