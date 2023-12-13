FROM openjdk:17-alpin
ENV APP_NAME cardapio
COPY ./target/${APP_NAME}.jar  /app/${APP_NAME}.jar
WORKDIR /app
CMD java -jar ${APP_NAME}.jar
EXPOSE 8081