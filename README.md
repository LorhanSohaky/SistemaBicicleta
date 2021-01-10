# Quero Bike
Este é um projeto para a disciplina [DESENVOLVIMENTO DE SOFTWARE PARA A WEB 1](https://github.com/delanobeder/DSW1) e tem como objetivo apresentar como é feito o desenvolvimento de sistemas webs. Para guiar o desenvolvimento foi disponibilizada uma lista de [REQUISITOS](./REQUIREMENTS.md).

Antes de iniciar o desenvolvimento em nível de código foi criado um [mockup das primeiras telas](https://www.figma.com/file/cLglVX5tENwQjAlL9EUDWo/QueroBike) para fornecer um guia de como estruturar as páginas. A ideia era prototipar todas as páginas antes do desenvolvimento, mas acabou sendo algo bastante demorado então ficou poucas páginas.

## Como instalar?
O projeto depende do [Java 11](https://openjdk.java.net/projects/jdk/11/), [Maven 3.6.3](https://maven.apache.org/download.cgi) e [Tomcat 9](https://tomcat.apache.org/download-90.cgi). Caso tenha alguma dúvida pode usar o [guia rápido de instalação](https://github.com/delanobeder/DSW1/blob/master/software.md).

## Como executar? TODO
Para executar basta fazer os seguintes passos:
1. Executar o Tomcat
  - Pode executar usando `./catalina.sh run` ou `./catalina.bat run`
2. Fazer deploy da aplicação
  - Para isso basta ir para dentro da pasta `QueroBike` e executar `mvn tomcat7:redeploy`
3. Agora a aplicação já está praticamente pronta, só falta popular o banco de dados
  - Pra isso basta acessar a rota `/reset-database`, lembrando de adicionar o contexto da aplicação caso necessário (`QueroBike/reset-database`).

Se tudo der certo aparecerá uma lista de cidades. ;-)

### Usuários Defaults
No caso de locadoras, existe a usuária `larissapeixoto@araujo.br`, 
TODO: no caso de clientes existe `customer@mailinator.com` e, no caso de administradores existe `admin1@mailinator.com`, todos com a senha `password123`. Caso queira ver os demais usuários basta acessar os arquivos `.sql`.

## Observações
Como o acesso de admin é mais restritivo ele não aparece no menu, então só é possível acessar através da url `/admins/login`.

Na primeira etapa optamos por usar SQLite, mas o mesmo não possui uma boa integração com o Spring, então tivemos de deixar de utilizar o sqlite e passar a usar o MySQL.

Para trazer um pouco mais de segurança ao projeto optamos por utilizar [Argon2](https://en.wikipedia.org/wiki/Argon2) para criar um hash das senhas e em seguida armazená-las no banco de dados, assim o projeto se aproxima ainda mais de um projeto real.

Quando criamos a tabela de cidade, optamos por deixar a `cidade` e `estado` como chave composta, mas isso gerou vários problemas (principalmente quando tinha acentuação) por ser comparação com string, para contornar criamos IDs numéricos.

A inicialização  do projeto é um pouco lento pelo fato dele popular todo o banco de dados.

### Problemas conhecidos
- 

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
- [ ] Página para o cliente atualizar seus dados (R1)
  - Interface
  - Internacionalização
  - Controller
  - DAO
- [ ] Página para o administrador atualizar os dados do cliente (R1)
  - Interface - Lorhan
  - Internacionalização - Lorhan
  - Controller - Lorhan
  - DAO - Lorhan
- [x] Opção para remover conta do cliente (R1)
  - Controller - Lorhan
  - DAO - Lorhan
- [x] Página de login da locadora (R2)
  - Interface - Lorhan
  - Internacionalização - Lorhan
  - Controller - Lorhan
  - DAO - Lorhan
- [ ] Página de home da locadora (R2)
- [x] Opção de remover locadora (R2)
  - DAO - Lorhan
  - Controller - Lorhan
  - Interface - Lorhan
  - Internacionalização - Lorhan
- [x] Opção para o admin atualizar os dados da locadora (R2)
  - DAO - Lorhan
  - Interface - Lorhan
  - Internacionalização - Lorhan
  - Controller - Lorhan
- [x] Opção para o admin cadastrar uma locadora (R2)
  - DAO - Lorhan
  - Interface - Lorhan
  - Internacionalização - Lorhan
  - Controller - Lorhan
- [x] Lista de locadoras (R3)
  - Internacionalização - Lorhan
  - Interface - Lorhan
  - Controller para listar as locadoras - Lorhan
  - DAO para listar as locadoras - Lorhan
- [ ] Lista de locadoras por cidade (R4)
  - Interface
  - Internacionalização
- [ ] Página para locação de bicicleta (R5) - Lucas
- [ ] Opção para listar as locações do cliente (R6) - Lucas
- [ ] Não deve permitir o cadastro de locações de um mesmo cliente ou de uma mesma locadora em um mesmo dia/horário (R7) - Lucas
- [ ] Página para listar todas as locações de uma locadora (R8) 
  - Internacionalização
  - Interface - Lucas
  - Model - Lucas
  - Controller - Lucas
  - DAO - Lucas
- [x] Página para o administrador listar as locadoras
  - Internacionalização - Lorhan
  - Interface - Lorhan
  - Controller para listar as locadoras - Lorhan
- [x] Componentes
  - Menu - Lorhan
  - Footer - Lorhan
- [x] Página inicial
  - Internacionalização - Lorhan
  - Interface - Lorhan

## Licença
Este projeto está licenciado sob a licença MIT e pode ser conferida [neste link](./LICENSE).

## Fontes
- [https://unsplash.com/photos/KoMiEAaFLsE](https://unsplash.com/photos/KoMiEAaFLsE)
- [https://www.iconfinder.com/icons/1055114/bike_bicycle_icon](https://www.iconfinder.com/icons/1055114/bike_bicycle_icon)
- [https://undraw.co/](https://undraw.co/)
- [https://www.iconfinder.com/icons/403017/avatar_default_head_person_unknown_user_anonym_icon](https://www.iconfinder.com/icons/403017/avatar_default_head_person_unknown_user_anonym_icon)
