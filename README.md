# Quero Bike
Este é um projeto para a disciplina [DESENVOLVIMENTO DE SOFTWARE PARA A WEB 1](https://github.com/delanobeder/DSW1) e tem como objetivo apresentar como é feito o desenvolvimento de sistemas webs. Para guiar o desenvolvimento foi disponibilizada uma lista de [REQUISITOS](./REQUIREMENTS.md).

Antes de iniciar o desenvolvimento em nível de código foi criado um [mockup das primeiras telas](https://www.figma.com/file/cLglVX5tENwQjAlL9EUDWo/QueroBike) para fornecer um guia de como estruturar as páginas. A ideia era prototipar todas as páginas antes do desenvolvimento, mas acabou sendo algo bastante demorado então ficou poucas páginas.

## Como instalar?
O projeto depende do [Java 11](https://openjdk.java.net/projects/jdk/11/), [Maven 3.6.3](https://maven.apache.org/download.cgi) e [Tomcat 9](https://tomcat.apache.org/download-90.cgi). Caso tenha alguma dúvida pode usar o [guia rápido de instalação](https://github.com/delanobeder/DSW1/blob/master/software.md).

## Como executar?
Para executar basta fazer os seguintes passos:
1. Executar o Tomcat
  - Pode executar usando `./catalina.sh run` ou `./catalina.bat run`
2. Fazer deploy da aplicação
  - Para isso basta ir para dentro da pasta `QueroBike` e executar `mvn tomcat7:redeploy`
3. Agora a aplicação já está praticamente pronta, só falta popular o banco de dados
  - Pra isso basta acessar a rota `/reset-database`, lembrando de adicionar o contexto da aplicação caso necessário (`QueroBike/reset-database`).

Se tudo der certo aparecerá uma lista de cidades. ;-)

### Usuários Defaults
No caso de locadoras, existe a usuária `piresbeatriz@lima.com`, no caso de clientes existe `customer@mailinator.com` e, no caso de administradores existe `admin1@mailinator.com`, todos com a senha `password123`. Caso queira ver os demais usuários basta acessar os arquivos `.sql`.

## Observações
Como o acesso de admin é mais restritivo ele não aparece no menu, então só é possível acessar através da url `admins/login`.

Para simplificar o desenvolvimento do projeto escolhemos utilizar o bando de dados [SQLite](https://en.wikipedia.org/wiki/SQLite). Desse modo não é necessário instalar nada, tendo em vista que a base de dados é armazenada em arquivo.

Para trazer um pouco mais de segurança ao projeto optamos por utilizar [Argon2](https://en.wikipedia.org/wiki/Argon2) para criar um hash das senhas e em seguida armazená-las no banco de dados, assim o projeto se aproxima ainda mais de um projeto real.

Além disso, configuramos o [SLF4J](http://www.slf4j.org/) para lidar com os logs do sistema e uma interface amigável de erro 404.

### Problemas conhecidos
- A inicialização do banco de dados é um pouco lenta e pode ter alguns erros devido ao lock do arquivo. Se isso acontecer será preciso apagar o arquivo do banco de dados, que está localizado na pasta do Apache Tomcat.

## TODOs
- [ ] Página de login do cliente (R1)
  - Interface
  - Internacionalização
  - Controller para realizar o login
  - DAO para buscar o cliente pelo email
- [ ] Página de cadastro do cliente (R1)
  - Interface
  - Internacionalização
  - Controller para realizar o cadastro do cliente
  - DAO para salvar o cliente no banco de dados
- [ ] Página de home do cliente (R1)
  - Interface
  - Controller
  - Internacionalização
- [ ] Página para atualizar os dados do cliente (R1)
  - Interface
  - Internacionalização
  - Controller
  - DAO
- [ ] Opção para remover conta do cliente (R1)
  - Controller
  - DAO
- [ ] Página de login da locadora (R2)
  - Interface
  - Internacionalização
  - Controller
  - DAO
- [ ] Página de home da locadora (R2)
- [ ] Opção de remover locadora (R2)
  - DAO
  - Controller
  - Interface
  - Internacionalização
- [ ] Opção para o admin atualizar os dados da locadora (R2)
  - DAO
  - Interface
  - Internacionalização
  - Controller
- [ ] Opção para o admin cadastrar uma locadora (R2)
  - DAO
  - Interface
  - Internacionalização
  - Controller
- [x] Lista de locadoras (R3)
  - Internacionalização - Lorhan
  - Interface - Lorhan
  - Controller para listar as locadoras - Lorhan
  - DAO para listar as locadoras
- [ ] Lista de locadoras por cidade (R4)
  - Interface
  - Internacionalização
- [ ] Página para locação de bicicleta (R5)
- [ ] Opção para listar as locações do cliente (R6)
- [ ] Não deve permitir o cadastro de locações de um mesmo cliente ou de uma mesma locadora em um mesmo dia/horário (R7)
- [ ] Página para listar todas as locações de uma locadora (R8)
  - Internacionalização
  - Interface
  - Model
  - Controller
  - DAO
- [ ] Página para o administrador listar as locadoras
  - Internacionalização
  - Interface
  - Controller para listar as locadoras
- [ ] Componentes
  - Menu
  - Footer
- [ ] Página inicial
  - Internacionalização
  - Interface

## Licença
Este projeto está licenciado sob a licença MIT e pode ser conferida [neste link](./LICENSE).

## Fontes
- [https://unsplash.com/photos/KoMiEAaFLsE](https://unsplash.com/photos/KoMiEAaFLsE)
- [https://www.iconfinder.com/icons/1055114/bike_bicycle_icon](https://www.iconfinder.com/icons/1055114/bike_bicycle_icon)
- [https://undraw.co/](https://undraw.co/)
- [https://www.iconfinder.com/icons/403017/avatar_default_head_person_unknown_user_anonym_icon](https://www.iconfinder.com/icons/403017/avatar_default_head_person_unknown_user_anonym_icon)
