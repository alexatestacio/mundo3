# Use a imagem base do OpenJDK 17
FROM adoptopenjdk:17-jdk-hotspot

# Define o diretório de trabalho no contêiner
WORKDIR /app

# Copia os arquivos do projeto para o diretório de trabalho
COPY . .

# Compila o projeto com o Maven
RUN mvn package

# Define as variáveis de ambiente para conexão com o PostgreSQL
ENV DB_HOST=db
ENV DB_PORT=5432
ENV DB_NAME=mydatabase
ENV DB_USER=myuser
ENV DB_PASSWORD=mypassword

# Expose a porta que o serviço irá utilizar
EXPOSE 8080

# Comando para iniciar o serviço
CMD ["java", "-jar", "target/myapp.jar"]