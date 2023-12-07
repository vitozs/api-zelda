# user-service

O user-service é uma API REST especializada em operações CRUD (Criar, Ler, Atualizar, Deletar) relacionadas aos perfis de usuário. Utilizando um banco de dados PostgreSQL, o serviço armazena as informações essenciais de cada usuário, incluindo id, nome e idade.

## Configuração do Ambiente

Siga os passos abaixo para configurar o ambiente e executar a aplicação.

### Pré-requisitos

Certifique-se de ter os seguintes requisitos instalados em sua máquina:

- Java JDK 17
- Gradle
- Docker

### Configuração do Banco de Dados

A aplicação utiliza o PostgreSQL como banco de dados. Para configurar o ambiente de desenvolvimento, siga os passos abaixo para criar um container PostgreSQL usando Docker.

1. Navegue até a pasta de recursos do projeto:

    ```bash
    cd /resources
    ```

2. Execute o seguinte comando para construir a imagem do Docker a partir do Dockerfile fornecido:

    ```bash
    docker build -t user-service-postgres .
    ```

3. Agora, inicie um container PostgreSQL usando a imagem recém-criada:

    ```bash
    docker run -d -p 5432:5432 --name user-service-container-postgres -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=JAVA17 -e POSTGRES_DB=apiZelda user-service-postgres
    ```

4. O container PostgreSQL agora está em execução, e a aplicação Spring Boot poderá se conectar a ele.

## Executando a Aplicação

Agora que o ambiente está configurado, siga os passos abaixo para executar a aplicação:

1. Abra um terminal e navegue até o diretório raiz do projeto.

2. Execute o seguinte comando para iniciar a aplicação usando o Gradle `bootRun`:

    ```bash
    ./gradlew bootRun
    ```

    ou, se estiver no Windows:

    ```bash
    gradlew bootRun
    ```

A aplicação estará disponível em http://localhost:8085. Você pode acessar a API através dessa URL e começar a interagir com os endpoints fornecidos.
