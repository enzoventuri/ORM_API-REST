# COVID-19 Data API

API REST desenvolvida em **Java com Spring Boot** para disponibilizar dados epidemiológicos, demográficos e de vacinação consolidados a partir da base pública **Our World in Data (OWID)**.

O projeto implementa uma camada de **Mapeamento Objeto-Relacional (ORM)** utilizando **Spring Data JPA/Hibernate**, mapeando um banco de dados PostgreSQL estruturado no schema `covid` e expondo consultas por meio de endpoints RESTful versionados.

---

## Sobre o projeto

A aplicação foi desenvolvida com o objetivo de fornecer uma interface REST para consumo de dados relacionados à pandemia de COVID-19.

A base de dados possui informações organizadas em diferentes níveis:

- Localidades e países;
- Continentes;
- Tipos de localidades;
- Informações demográficas e econômicas;
- Observações epidemiológicas por data;
- Observações de vacinação por data;
- Resumos consolidados das localidades.

O projeto utiliza o banco PostgreSQL como fonte de dados e o Hibernate como implementação JPA para realizar o mapeamento entre as tabelas relacionais e as entidades Java.

> **Nota:** O projeto mapeia entidades adicionais do schema `covid` que não estão atualmente expostos através os Endpoints REST
---

## Funcionalidades

- Listagem paginada de localidades;
- Consulta detalhada de uma localidade por código ISO;
- Consulta do perfil demográfico e econômico de uma localidade;
- Consulta do histórico epidemiológico por período;
- Consulta consolidada dos dados mais recentes;
- Mapeamento de tabelas com chaves primárias simples;
- Mapeamento de chaves primárias compostas;
- Relacionamento `1:1` utilizando `@MapsId`;
- Mapeamento de view SQL;
- Consultas utilizando Spring Data JPA;
- API REST versionada;
- Integração com PostgreSQL.

---

## Arquitetura do banco de dados

O banco utiliza o schema:

```text
covid
```

### Principais tabelas

```text
location_type
       |
       |
continent
       |
       v
location
   |
   +------------------+
   |                  |
   v                  v
location_profile   observation_day
                       |
              +--------+--------+
              |                 |
              v                 v
epidemiology_observation   vaccination_observation
```

### `location_type`

Tabela de domínio responsável por identificar o tipo da localidade.

Chave primária:

```text
location_type_code
```

---

### `continent`

Tabela de referência para continentes.

Chave primária:

```text
continent_id
```

---

### `location`

Tabela principal das localidades.

Principais atributos:

```text
location_id
iso_code
name
```

Relacionamentos:

- `location` → `continent`
- `location` → `location_type`

---

### `location_profile`

Armazena informações demográficas e econômicas associadas a uma localidade.

Principais atributos:

```text
location_id
population
gdp_per_capita
human_development_index
```

A tabela possui relacionamento **1:1** com `location`, compartilhando a mesma chave primária.

No JPA, esse relacionamento é representado utilizando:

```java
@OneToOne
@MapsId
```

---

### `observation_day`

Representa o grão canônico das séries temporais.

Sua chave primária é composta por:

```text
location_id
observation_date
```

Essa estrutura permite identificar uma observação específica de uma localidade em determinada data.

No JPA, a chave é representada por uma classe `@Embeddable` utilizada através de `@EmbeddedId`.

---

### `epidemiology_observation`

Armazena as observações epidemiológicas relacionadas a uma localidade e uma data.

A chave primária é composta e referencia diretamente `observation_day`.

Entre os dados disponibilizados estão informações relacionadas a:

- novos casos;
- novos óbitos;
- casos acumulados;
- óbitos acumulados;
- outros indicadores epidemiológicos presentes na base.

---

### `vaccination_observation`

Armazena as observações relacionadas à vacinação.

Assim como `epidemiology_observation`, sua identificação é baseada na combinação:

```text
location_id
observation_date
```

---

### `vw_latest_country_summary`

View utilizada para disponibilizar um resumo consolidado dos dados mais recentes por país.

A view é mapeada como uma entidade somente para leitura, permitindo que a API consulte os dados consolidados sem duplicar a lógica de agregação na aplicação.

---

## Tecnologias

| Tecnologia | Utilização |
|---|---|
| Java | Linguagem principal |
| Spring Boot | Framework da aplicação |
| Spring Web | Desenvolvimento da API REST |
| Spring Data JPA | Persistência e repositories |
| Hibernate | Implementação JPA / ORM |
| PostgreSQL | Banco de dados |
| Maven | Gerenciamento de dependências |
| Jakarta Persistence | Mapeamento das entidades |

---

## Estrutura do projeto

```text
src/
└── main/
    ├── java/
    │   └── .../
    │       ├── composite_ids/
    │       │   └── ObservationDayId.java
    │       │
    │       ├── config/
    │       │   └── OpenApiConfiguration.java
    │       │
    │       ├── controller/
    │       │   ├── LatestCountrySummaryViewController.java
    │       │   └── LocationController.java
    │       │
    │       ├── dto/
    │       │   └── response/
    │       │       ├── EpidemiologyObservationResponse.java
    │       │       ├── ErrorResponse.java
    │       │       ├── LatestCountrySummaryViewResponse.java
    │       │       ├── LocationProfileResponse.java
    │       │       └── LocationResponse.java
    │       │    
    │       ├── entity/
    │       │   ├── continent/
    │       │   │   └── Continent.java
    │       │   │
    │       │   ├── load/
    │       │   │   └── EtlLoad.java
    │       │   │
    │       │   ├── location/
    │       │   │   ├── Location.java
    │       │   │   ├── LocationProfile.java
    │       │   │   └── LocationType.java
    │       │   │
    │       │   ├── observation/
    │       │   │   ├── EpidemiologyObservation.java
    │       │   │   ├── ExcessMortalityObservation.java
    │       │   │   ├── HospitalizationObservation.java
    │       │   │   ├── ObservationDay.java
    │       │   │   ├── PolicyObservation.java
    │       │   │   ├── TestingObservation.java
    │       │   │   └── VaccinationObservation.java
    │       │   │
    │       │   ├── raw/
    │       │   │   └── StgOwidCovidRaw.java
    │       │   │
    │       │   └── testunit/
    │       │       └── TestUnit.java
    │       │ 
    │       ├── exceptions/
    │       │   └── GlobalExceptionHandler.java
    │       │
    │       ├── mapper/
    │       │   ├── EpidemiologyObservationMapper.java
    │       │   ├── LocationMapper.java
    │       │   └── LocationProfileMapper.java
    │       │
    │       └── repository/
    │           ├── LocationRepository.java
    │           ├── LocationProfileRepository.java
    │           ├── EpidemiologyObservationRepository.java
    │           └── LatestCountrySummaryRepository.java
    │   
    │    
    └── resources/
        └── application.yml
```

A separação entre `controller`, `service`, `repository`, `entity`, `mapper` e `dto` mantém as responsabilidades bem definidas. Os mappers realizam a conversão das entidades para os DTOs de resposta, enquanto o `GlobalExceptionHandler` centraliza o tratamento de exceções, facilitando a manutenção e evolução da aplicação.

---

## Mapeamento ORM

Todas as entidades relacionadas às tabelas do banco utilizam explicitamente o schema `covid`.

Exemplo:

```java
@Entity
@Table(schema = "covid", name = "location")
public class Location {
    // ...
}
```

### Chaves compostas

As tabelas de séries temporais utilizam uma chave composta representada por uma classe `@Embeddable`.

Exemplo conceitual:

```java
@Embeddable
public class ObservationDayId implements Serializable {

    private Long locationId;

    private LocalDate observationDate;
}
```

A entidade utiliza essa classe através de:

```java
@EmbeddedId
private ObservationDayId id;
```

Esse modelo representa corretamente a estrutura relacional formada por:

```text
location_id + observation_date
```

---

## Relacionamento 1:1

`LocationProfile` possui uma relação de um para um com `Location`.

O relacionamento utiliza chave compartilhada através de `@MapsId`:

```java
@OneToOne
@MapsId
@JoinColumn(name = "location_id")
private Location location;
```

Essa abordagem mantém o modelo Java alinhado à estrutura do banco, evitando a criação de uma chave artificial para `location_profile`.

---

## API REST

A API utiliza versionamento através do prefixo:

```text
/api/v1
```

### Endpoints

| Método | Endpoint | Descrição |
|---|---|---|
| `GET` | `/api/v1/locations` | Lista localidades com paginação |
| `GET` | `/api/v1/locations/{isoCode}` | Retorna os dados de uma localidade |
| `GET` | `/api/v1/locations/{isoCode}/profile` | Retorna o perfil demográfico e econômico |
| `GET` | `/api/v1/locations/{isoCode}/epidemiology` | Retorna o histórico epidemiológico |
| `GET` | `/api/v1/dashboards/latest-summary` | Retorna o resumo mais recente de todos os países |

---

## Exemplos de utilização

### Listar localidades

```http
GET /api/v1/locations?page=0&size=20
```

Retorna uma lista paginada de localidades.

---

### Consultar uma localidade

```http
GET /api/v1/locations/BRA
```

Retorna os dados associados ao código ISO informado.

Exemplo de código:

```text
BRA
```

---

### Consultar perfil da localidade

```http
GET /api/v1/locations/BRA/profile
```

Retorna informações como:

```text
population
gdp_per_capita
human_development_index
```

---

### Consultar histórico epidemiológico

```http
GET /api/v1/locations/BRA/epidemiology?startDate=2020-01-01&endDate=2021-12-31
```

Os parâmetros `startDate` e `endDate` delimitam o intervalo da consulta.

Formato esperado:

```text
YYYY-MM-DD
```

---

### Consultar resumo consolidado

```http
GET /api/v1/dashboards/latest-summary
```

Retorna os dados mais recentes disponibilizados pela view:

```text
vw_latest_country_summary
```

---

## Paginação

O endpoint de localidades utiliza o mecanismo de paginação do Spring Data:

```http
GET /api/v1/locations?page=0&size=20
```

Onde:

- `page` representa o número da página, iniciando em `0`;
- `size` representa a quantidade de registros por página.

Esse mecanismo evita o carregamento de toda a tabela em uma única requisição e permite que a API trabalhe de maneira mais eficiente com grandes volumes de dados.

---

## Configuração

A aplicação utiliza PostgreSQL como banco de dados.

As propriedades de conexão devem ser configuradas no arquivo:

```text
src/main/resources/application.properties
```

Exemplo:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/covid
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.properties.hibernate.default_schema=covid
spring.jpa.show-sql=false
```

> As credenciais reais do banco não devem ser versionadas no Git.

Para ambientes reais, recomenda-se utilizar variáveis de ambiente ou um mecanismo de gerenciamento de secrets.

---

## Pré-requisitos

Antes de executar a aplicação, é necessário possuir:

- Java instalado;
- Maven instalado ou Maven Wrapper disponível;
- PostgreSQL em execução;
- Banco de dados contendo o schema `covid`;
- Tabelas e view previamente criadas.

---

## Executando o projeto

Clone o repositório:

```bash
git clone <repository-url>
```

Entre no diretório:

```bash
cd <project-directory>
```

Execute a aplicação utilizando Maven:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

Alternativamente:

```bash
mvn spring-boot:run
```

Após a inicialização, a API estará disponível na porta configurada pela aplicação.

Por padrão:

```text
http://localhost:8080
```

---

## Testando a API

Com a aplicação em execução, alguns exemplos de requisições são:

```bash
curl "http://localhost:8080/api/v1/locations?page=0&size=20"
```

```bash
curl "http://localhost:8080/api/v1/locations/BRA"
```

```bash
curl "http://localhost:8080/api/v1/locations/BRA/profile"
```

```bash
curl "http://localhost:8080/api/v1/locations/BRA/epidemiology?startDate=2020-01-01&endDate=2021-12-31"
```

```bash
curl "http://localhost:8080/api/v1/dashboards/latest-summary"
```

---

## Princípios adotados

O projeto segue alguns princípios importantes no desenvolvimento da API:

### RESTful

Os recursos são representados por URLs semânticas e os métodos HTTP são utilizados de acordo com sua finalidade.

### Versionamento

A API utiliza:

```text
/api/v1
```

permitindo evoluções futuras sem necessariamente quebrar consumidores existentes.

### Separação de responsabilidades

As responsabilidades são divididas entre:

```text
  Client
    ↓
Controller
    ↓
Repository
    ↓
Database
    ↓
 Entity
    ↓
 Mapper
    ↓
Response DTO
    ↓
Controller
    ↓
 Client

```

Isso reduz o acoplamento e facilita testes e manutenção.

### Paginação

Consultas potencialmente grandes utilizam paginação para reduzir consumo de memória e tráfego de rede.

### ORM

O modelo de objetos Java representa explicitamente a estrutura relacional do banco, incluindo:

- relacionamentos;
- chaves compostas;
- chaves compartilhadas;
- referências entre tabelas;
- view de consolidação.

---

## Modelo de dados

A estrutura conceitual das principais relações é:

```text
LocationType
    │
    └──────────────┐
                   │
Continent ─────────┤
                   │
                   ▼
               Location
               │       │
               │       └────── 1:1 ────── LocationProfile
               │
               ▼
        ObservationDay
        │            │
        │            │
        ▼            ▼
 Epidemiology    Vaccination
 Observation    Observation
```

O `ObservationDay` funciona como o ponto central das séries temporais, permitindo que os dados epidemiológicos e vacinais sejam associados à combinação única de localidade e data.

---

## Fonte dos dados

Os dados utilizados como origem do projeto são provenientes do:

**Our World in Data (OWID)**

A base foi previamente processada e disponibilizada em PostgreSQL para consumo pela aplicação.

---

## Objetivo acadêmico/técnico

Este projeto demonstra a aplicação prática de conceitos de:

- Desenvolvimento de APIs REST;
- Spring Boot;
- Spring Data JPA;
- Hibernate;
- Mapeamento Objeto-Relacional;
- PostgreSQL;
- Relacionamentos entre entidades;
- Chaves primárias compostas;
- `@Embeddable`;
- `@EmbeddedId`;
- `@MapsId`;
- Paginação;
- Consultas por intervalo de datas;
- Mapeamento de views SQL;
- Organização de aplicações backend.

---

## Status

**Em desenvolvimento**

Novos endpoints, validações, tratamento de erros, testes automatizados e documentação da API podem ser adicionados conforme a evolução do projeto.

---

## Licença

Este projeto foi desenvolvido para fins educacionais e de demonstração técnica.

Consulte os termos de uso e licenciamento das fontes de dados utilizadas antes de redistribuir os dados originais.