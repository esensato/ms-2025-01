## Projeto Cidade Springfield

[Springfield](https://springfield-nj.us/about-springfield/) é uma cidade moderna e que deseja oferecer aos seus cidadãos serviços on-line para agilizar o atendimento às demandas da população.

Atualmente ela possui apenas uma base com alguns dados de seus moradores armazenados em uma base *SQL Server* hospedada na *Azure Cloud*. Os dados dos cidadãos estão armazenados na tabela (já criada) `CAD_CIDADAO` com a estrutura abaixo:

```sql
CREATE TABLE CAD_CIDADAO (
    ID INT PRIMARY KEY CHECK (ID BETWEEN 10000 AND 99999),  -- ID
    nome VARCHAR(255),  -- Nome do cidadão
    endereco VARCHAR(255),  -- Endereço do cidadão
    bairro VARCHAR(255)  -- Bairro do cidadão
);
```

- Para conexão ao *SQL Server* incluir as seguintes dependências:
```xml
<dependencyManagement>
    <dependencies>
        <dependency>
        <groupId>com.azure.spring</groupId>
        <artifactId>spring-cloud-azure-dependencies</artifactId>
        <version>5.20.0</version>
        <type>pom</type>
        <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```
- Incluir também:
```xml
<dependency>
    <groupId>com.microsoft.sqlserver</groupId>
    <artifactId>mssql-jdbc</artifactId>
    <scope>runtime</scope>
</dependency>

<dependency>
    <groupId>com.azure.spring</groupId>
    <artifactId>spring-cloud-azure-starter</artifactId>
</dependency>
```
1. Criar `end points` para:
    1. Listar todos os cidadãos presentes na base;
    1. Permitir a consulta de um cidadão pelo seu número (`ID`) e retornar os seus dadods;
    1. Possibilitar o cadastro de um novo cidadão;
    1. Permitir a atualização dos dados de um cidadão;
2. Criar um **micro serviço** que permita o cadastro de um cidadão com a definição de um `username` e `senha` utilizando a base de dados de cidadãos para validar o código do usuário. Incluir neste **micro serviço** a possibilidade de:
    1. Trocar a senha;
    1. Bloqueio de usuário após 3 tentativas sem sucesso de *login*;
    1. Somente permitir um único cadastro de usuário por `ID`;
    1. Usuário tem que trocar a senha caso fique mais de 30 dias sem efetuar *login*;
    1. Desbloqueio de usuário bloqueado;

