# Sistema para locação de bicicletas

_Prof. Delano M. Beder (UFSCar)_

O sistema deve possuir um cadastro de clientes, com os seguintes dados:
- e-mail
- senha
- CPF
- nome
- telefone
- sexo
- data de nascimento

O sistema deve possuir um cadastro de locadoras, com os seguintes dados: 
- e-mail
- senha
- CNPJ
- nome
- cidade

O sistema deve possuir um cadastro de locações, com os seguintes dados: 
- CPF do cliente
- CNPJ da locadora
- dia/horário da locação.

Assume-se que a duração da locação é de 1 hora e sempre inicia-se em "hora cheia" (13h 00min etc)

O sistema deve atender aos seguintes requisitos:
1. CRUD1 de clientes (requer login de administrador)
2. CRUD de locadoras (requer login de administrador)
3.  Listagem de todos as locadoras em uma única página (não requer login)
4.  Listagem de todos as locadoras por cidade (não requer login)
5.  Locação de uma bicicleta em uma locadora (requer login do cliente via e-mail + senha). Depois de fazer login, o cliente pode cadastrar uma locação. Para isso, deve escolher uma locadora (escolhendo a partir de uma lista), uma data, e deve ser gravado a locação na base de dados.
6.  Listagem de todas as locações de um cliente (requer login do cliente via e-mail + senha). Depois de fazer login, o cliente pode visualizar todas as suas locações gravadas.
7.  O sistema não deve permitir o cadastro de locações de um mesmo cliente ou de uma mesma locadora em um mesmo dia/horário.
8.  Listagem de todas as locações de uma locadora (requer login da locadora via e-mail +senha). Depois de fazer login, a locadora pode visualizar todas as suas locações gravadas.
9. O sistema deve ser internacionalizado em pelo menos dois idiomas: português + outro de sua escolha.

O sistema deve tratar todos os erros possíveis (cadastros duplicados, problemas técnicos, etc) mostrando uma página de erros amigável ao usuário e registrando o erro no console.