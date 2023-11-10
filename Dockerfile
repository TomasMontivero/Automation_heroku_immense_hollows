# Base image for JDK
FROM eclipse-temurin:21-jdk-alpine

# Set working dorectory
WORKDIR /app

# Copy Maven files
COPY pom.xml ./

# Maven configuration
ENV MAVEN_HOME /usr/share/maven
COPY --from=maven:3.9.5-eclipse-temurin-11 ${MAVEN_HOME} ${MAVEN_HOME}
COPY --from=maven:3.9.5-eclipse-temurin-11 /usr/local/bin/mvn-entrypoint.sh /usr/local/bin/mvn-entrypoint.sh
COPY --from=maven:3.9.5-eclipse-temurin-11 /usr/share/maven/ref/settings-docker.xml /usr/share/maven/ref/settings-docker.xml
RUN ln -s ${MAVEN_HOME}/bin/mvn /usr/bin/mvn
ARG MAVEN_VERSION=3.9.5
ARG USER_HOME_DIR="/root"
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
ENTRYPOINT ["/usr/local/bin/mvn-entrypoint.sh"]
CMD ["mvn"]

# Download/install Maven dependencies
RUN mvn -B -f pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve

# Copy all code files
COPY src ./src

# Command to run in the container
RUN mvn clean install
CMD ["mvn", "spring-boot:run"]

# Test
RUN echo "hello world"