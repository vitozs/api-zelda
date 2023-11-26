FROM postgres
ENV POSTGRES_PASSWORD='admin'
ENV POSTGRES_DB='user_db'
COPY ./init.sql /docker-entrypoint-initdb.d/
