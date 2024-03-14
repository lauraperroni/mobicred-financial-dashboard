# Dashboard Financeiro

## Visão Geral:

O Dashboard Financeiro é uma aplicação full-stack desenvolvida para fornecer aos usuários uma visão abrangente de sua situação financeira, permitindo o acompanhamento detalhado de contas, entradas e saídas, metas financeiras, notificações e análises gráficas. O projeto visa auxiliar os usuários na gestão eficaz de suas finanças pessoais, fornecendo ferramentas e recursos intuitivos para tomada de decisões financeiras informadas.

## Escopos Iniciais - Funcionalidades:

**Contas e Saldo Atual:**  
Os usuários poderão visualizar uma lista de contas associadas, juntamente com o saldo atual de cada conta.  
O saldo atual será atualizado automaticamente com base nas transações registradas na aplicação (Microsserviço externo).

**Período:**  
Os usuários poderão selecionar e visualizar transações dentro de um período específico, como dia, semana, mês ou ano.

**Entradas e Saídas:**  
Os usuários poderão registrar suas entradas (receitas) e saídas (despesas) financeiras, categorizando-as adequadamente.  
As transações serão exibidas em uma interface amigável, permitindo aos usuários revisar e gerenciar facilmente seus registros financeiros.

**Despesas por Categorias:**  
Os usuários poderão visualizar suas despesas categorizadas por tipos específicos, como alimentação, transporte, moradia, entretenimento, etc.  
Isso proporcionará uma compreensão mais clara dos padrões de gastos e ajudará na identificação de áreas para economia.

**Ranqueamento de Gastos:**  
Os usuários terão acesso a um ranking das principais categorias de gastos, mostrando onde a maior parte de seus gastos está sendo direcionada.

**Evolução Pessoal:**  
Os usuários poderão acompanhar a evolução de seu patrimônio líquido ao longo do tempo, observando o saldo atual e as tendências de gastos e receitas.

**Metas e Dinheiro Guardado:**  
Os usuários poderão estabelecer metas financeiras, como economizar para uma viagem ou pagar uma dívida, e acompanhar seu progresso em direção a essas metas.  
O dashboard fornecerá informações sobre quanto dinheiro foi guardado em relação às metas estabelecidas.

**Notificações de Todas Funcionalidades:**  
Os usuários receberão notificações e alertas para transações importantes, aproximação de metas, variações significativas nos gastos, entre outros eventos relevantes.

**Variação de Despesas:**  
Os usuários poderão visualizar gráficos e relatórios detalhados sobre a variação de suas despesas ao longo do tempo, facilitando a análise de tendências e padrões.

**Gráficos em Geral:**  
A aplicação fornecerá uma variedade de gráficos e visualizações de dados para representar as informações financeiras de forma clara e intuitiva.

**Visualização de Histórico:**  
Os usuários poderão visualizar um histórico detalhado de suas transações financeiras, permitindo uma análise retrospectiva de seu comportamento financeiro.

**Login e Cadastro:**  
Utilização de login e cadastro de perfis de usuários, utilizando a segurança adequada e as verificações necessárias.

## Tecnologias Utilizadas:

**Frontend (React):**  
- React.js  
- Material-UI ou Bootstrap para estilização

**Backend (Spring):**  
- Spring Boot  
- Spring Security para autenticação e autorização  
- Spring Data JPA para acesso e manipulação de dados  
- Banco de dados relacional (por exemplo, MySQL, PostgreSQL)

**Ferramentas Adicionais:**  
- Docker para empacotamento e implantação da aplicação em contêineres  
- Git para controle de versão e colaboração

## Considerações Finais:

O Dashboard Financeiro é uma solução abrangente para o gerenciamento financeiro pessoal, oferecendo uma ampla gama de funcionalidades para auxiliar os usuários na gestão eficaz de suas finanças. O projeto será desenvolvido seguindo as melhores práticas de desenvolvimento e utilizando tecnologias modernas para fornecer uma experiência de usuário intuitiva e agradável.


## Diagrama do Banco de Dados
![Diagrama](/img/Diagrama%20BD.png)


## Postman Endpoints to test

### Usuarios (/usuarios)

| Endpoint        | Tipo   | Descrição                       |
|-----------------|--------|---------------------------------|
| /usuarios/all            | GET    | Traz lista de todos os usuários |
| /usuarios/{id}           | GET    | Traz um usuário pelo seu Id     |
| /usuarios/novo           | POST   | Cria um novo usuário            |
| /usuarios/atualizar/{id} | PUT    | Atualiza um usuário pelo seu Id |
| /usuarios/deletar/{id}   | DELETE | Deleta usuário pelo seu Id      |


### Transacoes (/transacoes)

| Endpoint                   | Tipo   | Descrição                          |
|----------------------------|--------|------------------------------------|
| /transacoes/all            | GET    | Traz lista de todas as transações  |
| /transacoes/{id}           | GET    | Traz uma transação pelo seu Id     |
| /transacoes/novo           | POST   | Cria uma nova transação            |
| /transacoes/atualizar/{id} | PUT    | Atualiza uma transação pelo seu Id |
| /transacoes/deletar/{id}   | DELETE | Deleta transação pelo seu Id       |