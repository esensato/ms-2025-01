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
- As propriedades devem ser definidas (PEGAR A SENHA DENTRO DA ATIVIDADE NO CANVAS!!!!!):

    ```
    spring.application.name=springfield
    logging.level.org.hibernate.SQL=DEBUG
    spring.datasource.url=jdbc:sqlserver://db-springfield.database.windows.net:1433;database=springfield;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;
    spring.datasource.username=dbuser@db-springfield
    spring.datasource.password=
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServer2016Dialect
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
3. Cada cidadão pode utilizar os serviços do portal para realizar diversas solicitações. A fim de controlar o fluxo dessas solicitações é necessário implementar, com o **Spring State Machine** um registro de histórico contendo o `ID` do cidadão, e a data em que o estado foi registrado.
    1. Os estados possíveis são: SOLICITADO, AGUARDANDO_ANALISE e CONCLUIDO
    1. As ações são: ANALISAR e CONCLUIR
    1. Registrar em banco de dados os dados obtidos entre os diferentes estados
    1. Criar um *endpoint* para visualizar o histórico de um cidadão passando como parâmetro o seu `ID`
