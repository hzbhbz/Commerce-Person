FROM gradle:7.1.0-jdk11

USER root

RUN useradd -ms /bin/bash wasadm

RUN rm -rf /app
RUN mkdir -p /app

RUN chown -R wasadm:wasadm /app

RUN cd /app
RUN git clone https://github.com/hzbhbz/Jennifer.git /app/agent.java
RUN git clone https://github.com/hzbhbz/Commerce-Common.git /app/Commerce-Common
RUN git clone https://github.com/hzbhbz/Commerce-Person.git /app/Commerce-Person

WORKDIR /app/Commerce-Person

RUN cd /app/Commerce-Person

RUN chmod -R 755 /app/*

RUN gradle build

CMD ["java", "-javaagent:/app/agent.java/jennifer.jar", "-Djennifer.config=/app/agent.java/conf/commerce-person-v2.conf", "-jar", "/app/Commerce-Person/build/libs/Commerce-Person-0.0.1-SNAPSHOT.jar"]
