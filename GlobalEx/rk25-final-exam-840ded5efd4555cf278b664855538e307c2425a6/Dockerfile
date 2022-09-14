# syntax=docker/dockerfile:1
# Which "official java image"
FROM openjdk:11
# working dir
WORKDIR /app
# copy from Host(pc, lap) => container
COPY mvnw pom.xml ./
COPY .mvn/ .mvn
# run this inside the image
RUN ./mvnw dependency:go-offline
COPY src ./src
#run inside container
CMD ["./mvnw", "spring-boot:run"]