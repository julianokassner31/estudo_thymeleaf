FROM adoptopenjdk:8-jre
WORKDIR /app
COPY ./target/controle-estoque-0.0.1-SNAPSHOT.jar controle-estoque.jar
ENV URL_DB = $URL_DB
ENV USER_DB = $USER_DB
ENV PASS_DB = $PASS_DB
ENTRYPOINT  ["java", "-jar", "-Dspring.datasource.url=${URL_DB}", "-Dspring.datasource.username=${USER_DB}", "-Dspring.datasource.password=${PASS_DB}", "-Dspring.datasource.driver-class-name=com.mysql.jdbc.Driver", "-Dspring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect", "controle-estoque.jar"]
EXPOSE 8080