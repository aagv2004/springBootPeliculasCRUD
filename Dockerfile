FROM eclipse-temurin:22-jdk AS buildstage 



RUN apt-get update && apt-get install -y maven



WORKDIR /app



COPY pom.xml .

COPY src /app/src

COPY Wallet_J5Q4B1NOB2T8V68G /app/wallet



ENV TNS_ADMIN=/app/wallet



RUN mvn clean package



FROM eclipse-temurin:22-jdk 



COPY --from=buildstage /app/target/peliculas-0.0.1-SNAPSHOT.jar /app/peliculas.jar



COPY Wallet_J5Q4B1NOB2T8V68G /app/wallet



ENV TNS_ADMIN=/app/wallet

EXPOSE 8080



ENTRYPOINT [ "java", "-jar","/app/peliculas.jar" ]