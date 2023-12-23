FROM postgres
ENV POSTGRES_PASSWORD='admin'
ENV POSTGRES_DB='zelda-api'
COPY ./init.sql /docker-entrypoint-initdb.d/
EXPOSE 5432
