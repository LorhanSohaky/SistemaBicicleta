# Quero Bike
Este é um projeto para a disciplina [DESENVOLVIMENTO DE SOFTWARE PARA A WEB 1](https://github.com/delanobeder/DSW1) e tem como objetivo apresentar como é feito o desenvolvimento de sistemas webs. Para guiar o desenvolvimento foi disponibilizada uma lista de [REQUISITOS](./REQUIREMENTS.md).

Antes de iniciar o desenvolvimento em nível de código foi criado um [mockup das primeiras telas](https://www.figma.com/file/cLglVX5tENwQjAlL9EUDWo/QueroBike) para fornecer um guia de como estruturar as páginas. A ideia era prototipar todas as páginas antes do desenvolvimento, mas acabou sendo algo bastante demorado então ficou poucas páginas.

## Como instalar?

## Como executar?

## TODOs
- [x] Scripts SQL
  - Script de criação das tabelas - Lorhan
  - Script de inserção das cidades, administradores e locadoras - Lorhan
- [x] Página de login do cliente (R1)
  - Interface - Lucas e Lorhan
  - Internacionalização - Lorhan
  - Controller para realizar o login - Lorhan
  - DAO para buscar o cliente pelo email - Lorhan
- [x] Página de cadastro do cliente (R1)
  - Interface - Lucas e Lorhan
  - Internacionalização - Lorhan
  - Controller para realizar o cadastro do cliente - Lorhan
  - DAO para salvar o cliente no banco de dados - Lorhan
- [x] Página de home do cliente (R1)
  - Interface - Lucas e Lorhan
  - Controller para permitir acessar apenas se estiver logado - Lorhan
- [] Página para atualizar os dados do cliente (R1)
  - Controller para atualizar os dados do cliente - Lorhan
  - DAO para salvar as alterações no banco de dados - Lorhan
- [x] Opção para remover conta do cliente (R1)
  - Controller - Lorhan
  - DAO - Lorhan
- [] Página de login da locadora (R2)
- [] Página de home da locadora (R2)
- [] Opção de remover locadora (R2)
- [] Opção de atualizar dados da locadora (R2)
- [] Opção de cadastrar locadora (R2)
- [x] Lista de locadoras (R3)
  - Internacionalização - Lorhan
  - Interface - Lorhan
  - Controller para listar as locadoras - Lorhan
  - DAO para listar as locadoras - Lorhan
- [] Lista de locadoras por cidade (R4)
- [] Página para locação de bicicleta (R5)
- [] Página para listar as locações do cliente (R6)
- [] Página para realizar a locação de bicicletas (R7)
- [] Página para listar todas as locações de uma locadora (R8)
- [x] Componentes
  - Menu - Lorhan
  - Footer - Lorjan
- [x] Página inicial
  - Internacionalização - Lorhan
  - Interface - Lucas e Lorhan


## Observações
Para simplificar o desenvolvimento do projeto foi escolhido o bando de dados [SQLite](https://en.wikipedia.org/wiki/SQLite), desse modo não é necessário instalar nada, tendo em vista que a base de dados é armazenada em arquivo.

Para trazer um pouco mais de segurança ao projeto foi optado por utilizar [Argon2](https://en.wikipedia.org/wiki/Argon2) para criar um hash das senhas para então armazená-las no banco de dados, assim o projeto se aproxima ainda mais de um projeto real.

Além disso, configuramos o [SLF4J](http://www.slf4j.org/) para lidar com os logs do sistema e uma interface amigável de erro 404.


## Licenças
Este projeto está licenciado sob a licença MIT e pode ser conferida [neste link](./LICENSE).

