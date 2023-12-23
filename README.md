# Projeto Final API Zelda

Este projeto é uma API desenvolvida em Spring Boot utilizando o Gradle como gerenciador de dependências, voltada para entusiastas da série de jogos "The Legend of Zelda". A equipe de desenvolvedores responsável pelo projeto é composta por:
- [Bianca Gomes](https://github.com/bian-ka)
- [Robert de Carvalho](https://github.com/robertperquim)
- [Vitor Chagas](https://github.com/vitozs)

## Estrutura do Projeto

### API Gateway
A API Gateway funciona como o ponto de entrada unificado para os serviços oferecidos no projeto. Utilizando o Spring Cloud Gateway, ela realiza o roteamento eficiente das solicitações para os serviços específicos, como o `user-service` e `zelda-service`.

### user-service
O `user-service` é uma API REST especializada em operações CRUD relacionadas aos perfis de usuário. Utilizando o Spring Data JPA, ele se comunica com um banco de dados PostgreSQL para armazenar informações essenciais sobre os usuários.

### zelda-service
O `zelda-service` é dedicado à consulta da API pública da franquia Zelda. Ele fornece informações relevantes sobre jogos, personagens e outros elementos da série, permitindo que os usuários obtenham dados valiosos sobre seus jogos favoritos.

## Comunicação com Banco de Dados

O `user-service` utiliza o Spring Data JPA para interagir com um banco de dados PostgreSQL, armazenando informações importantes sobre os perfis de usuário. As operações CRUD são expostas como endpoints da API REST, proporcionando uma forma eficiente de gerenciar os dados dos usuários.

## Segurança e Autenticação

O projeto prioriza a segurança, implementando o Spring Security para proteger os recursos. A autenticação é realizada por meio de tokens JWT (JSON Web Token), gerados quando um usuário se autentica com sucesso. Esses tokens são utilizados nos cabeçalhos das solicitações subsequentes para autorizar o acesso aos recursos protegidos.

## Arquitetura de Microserviços

O projeto adota uma arquitetura de microserviços para garantir modularidade e escalabilidade. Cada serviço é uma entidade independente que pode ser escalada e mantida separadamente. A comunicação entre esses serviços é realizada principalmente por chamadas HTTP, proporcionando uma abordagem distribuída.

## Spring Cloud Gateway

O `gateway-service` utiliza o Spring Cloud Gateway para roteamento e encaminhamento eficientes das solicitações. Atuando como ponto de entrada unificado, redireciona as solicitações para os serviços apropriados com base em regras de roteamento configuradas.

## AWS Version

Além desta versão do projeto, existe uma versão que utiliza os serviços da AWS (Amazon Web Services). A versão AWS pode incluir diferentes implementações de banco de dados, autenticação e outros componentes, aproveitando os serviços gerenciados pela AWS.
