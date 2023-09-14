docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=mssqlPwd" \
   -p 1433:1433 --name estaciomssql --platform linux/amd64 --hostname estaciomssql \
   -d \
   mcr.microsoft.com/mssql/server:2022-latest
