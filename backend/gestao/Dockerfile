# Etapa 1: Construção da aplicação (usando uma imagem com Maven e OpenJDK)
FROM maven:3.8-openjdk-21 AS build

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo pom.xml e baixe as dependências (para otimizar o cache do Docker)
COPY pom.xml .

# Baixe as dependências do Maven
RUN mvn dependency:go-offline

# Copie o restante do código
COPY src ./src

# Compile o código e gere o JAR
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final para execução
FROM openjdk:21-jdk-slim

# Defina o diretório de trabalho no container
WORKDIR /app

# Copie o JAR gerado na etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Exponha a porta onde a aplicação irá rodar
EXPOSE 8080

# Comando para rodar a aplicação Spring Boot
CMD ["java", "-Xmx512m", "-Xms256m", "-jar", "app.jar"]
