#Start with a base image containing Java runtime
FROM openjdk:21-jdk-slim

# MAINTAINER instruction is deprecated in favor of using label
# MAINTAINER eazybytes.com
#Information around who maintains the image
LABEL "org.opencontainers.image.authors"="eazybytes.com"

# Add the application's jar to the image
COPY target/SpringAndSpringBoot-0.0.1-SNAPSHOT.jar SpringAndSpringBoot-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "SpringAndSpringBoot-0.0.1-SNAPSHOT.jar"]