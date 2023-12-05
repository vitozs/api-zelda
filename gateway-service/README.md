# gateway-service

O API Gateway para entusiastas da franquia Zelda é um ponto de entrada unificado para os serviços essenciais oferecidos. Desenvolvido com o Spring Cloud Gateway, o gateway integra de forma eficiente as APIs de Usuário (user-service) e de Consulta à API Pública da franquia Zelda (zelda-service).

## Configuração do Ambiente

Certifique-se de ter os seguintes requisitos instalados em sua máquina:

- Java JDK 17
- Gradle

Além disso, a aplicação depende do user-service e zelda-service para funcionar.

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



A aplicação estará disponível em http://localhost:8089. Você pode acessar a API através dessa URL e começar a interagir com os endpoints fornecidos.
