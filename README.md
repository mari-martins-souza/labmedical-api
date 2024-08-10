
# LABmedical API

The **LABmedical API** is a backend application built with Spring Boot, designed to manage patients, appointments, and exams in a hospital environment. This API provides endpoints to create, read, update, and delete (CRUD) information related to patients, appointments, and exams, as well as user authentication and authorization, implemented with Spring Security.

System permissions are divided among three types of users: **Admin, Doctor, and Patient**, each with different roles and permissions. This ensures that each user has access only to the functionalities and data relevant to their role.

## Features

**Authentication and Authorization:** Implemented with Spring Security, the API supports user authentication and defines access levels for three different types of users: Administrators, Doctors (or Nurses), and Patients. Authorization is managed through JWT (JSON Web Tokens), ensuring security and efficiency in access control.

**User Profiles:**

**ADMIN:** Has unrestricted access to all system resources.

**MEDICO(DOCTOR):** Has access to all available methods on all endpoints, except the `/users` endpoint, which allows the registration of new users. There is an exception for creating new users at the `/patients` endpoint, where, when registering a new patient, a user with the “PATIENT” permission is automatically created in the same request. Therefore, the DOCTOR user, who can register new patients, indirectly has the permission to register a user for the patients. However, the Doctor cannot directly register users at the `/users` endpoint, as this allows the creation of users with DOCTOR or ADMIN permissions, and only an ADMIN user has this permission.

**Login** (`/login`): Endpoint for system authentication.

**PACIENTE(PATIENT):** Has limited access to system resources, being able to view (GET) only their own information and related data (`/patients/{id}`, `/appointments/{id}`, /`exams/{id}`, and `/patients/{id}/medical-record`).

**Patient Management** (`/patients`): Endpoint to register, edit, list, and delete patients. Patients can only be deleted when they do not have exams or appointments linked to them.

**Appointment Management** (`/appointments`): Endpoint to register, edit, list, and delete appointments. Appointments are linked to patients.

**Exam Management** (`/exams`): Endpoint to register, edit, list, and delete exams. Exams are also linked to patients.

**Dashboard** (`/dashboard/stats`): Endpoint to obtain general statistics on the number of patients, appointments, and exams registered in the system.

**Medical Records** (`/patients/{id}/medical-record` and `/patients/medical-record-list`): Endpoints to consult the medical record of a specific patient, including all exams and appointments linked to them, and an endpoint to list all patients, including ID, name, and health plan of the patient.


## How to Test

1. Clone the project to your machine.
2. Ensure you have PostgreSQL, pgAdmin 4, and Insomnia installed. If not, you will need to install them.
3. Ensure you have an IDE with Java support. Suggestion: IntelliJ IDEA.
4. Open the project in your IDE.
5. Start pgAdmin 4.
6. Create a database in PostgreSQL named `labmedical`.
7. Configure the `application.properties` file of the project according to your environment credentials:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/labmedical
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
```
8. Start Insomnia and import the `Insomnia_2024-08-10.json` file located in the root folder of the project.
9. In your IDE, run the `LaBmedicalApplication` class to start the application.
10. Test the endpoints using Insomnia.

## Project Tools and Dependencies

This project was built using the following technologies and libraries:

**Development Tools:**

- IDE: IntelliJ IDEA
- Database: PostgreSQL
- Database Management: pgAdmin 4
- API Testing: Insomnia

**Maven Dependencies:**

- spring-boot-starter-data-jpa
- spring-boot-starter-oauth2-resource-server
- spring-boot-starter-security
- spring-boot-starter-validation
- spring-boot-starter-web
- postgresql
- spring-boot-starter-test
- spring-security-test
- jjwt-api
- slf4j-api
- slf4j-simple

- - - - - - - - - - - - - - - - - - -

# LABmedical API

A **LABmedical API** é uma aplicação backend construída com Spring Boot, projetada para gerenciar pacientes, consultas e exames em um ambiente hospitalar. Esta API fornece endpoints para criar, ler, atualizar e deletar (CRUD) informações relacionadas a pacientes, consultas e exames, além de autenticação e autorização de usuários, implementadas com Spring Security.

As permissões do sistema são divididas entre três tipos de usuários: **Admin, Médico e Paciente**, cada um com papéis e permissões diferentes. Isso garante que cada usuário tenha acesso apenas às funcionalidades e dados relevantes para seu papel.

## Recursos

**Autenticação e Autorização:** Implementada com Spring Security, a API suporta autenticação de usuários e define níveis de acesso para três tipos diferentes de usuários: Administradores, Médicos (ou Enfermeiros) e Pacientes. A autorização é gerenciada através de tokens JWT (JSON Web Tokens), garantindo segurança e eficiência no controle de acesso.

**Perfis de Usuário:**

**ADMIN:** Possui acesso irrestrito a todos os recursos do sistema.

**MEDICO:** Tem acesso a todos os métodos disponíveis em todos os endpoints, exceto ao endpoint `/users`, que permite o cadastro de novos usuários. Há uma exceção para a criação de novos usuários no endpoint `/patients`, onde, ao cadastrar um novo paciente, um usuário com a permissão “PACIENTE” é criado automaticamente na mesma requisição. Portanto, o usuário do tipo MEDICO, que pode cadastrar novos pacientes, indiretamente, tem a permissão de cadastrar um usuário para os pacientes. No entanto, o Médico não pode cadastrar usuários diretamente no endpoint `/users`, pois este permite a criação de usuários com permissões de MEDICO ou ADMIN, e apenas um usuário do tipo ADMIN tem essa permissão.

**Login** (`/login`): Endpoint para autenticação no sistema.

**PACIENTE:** Possui acesso limitado aos recursos do sistema, podendo visualizar (GET) apenas suas próprias informações e dados relacionados (`/patients/{id}`, `/appointments/{id}`, `/exams/{id}` e `/patients/{id}/medical-record`).

**Gerenciamento de Pacientes** (`/patients`): Endpoint para cadastrar, editar, listar e deletar pacientes. Pacientes somente poderão ser deletados quando não possuírem exames ou consultas vinculados a eles.

**Gerenciamento de Consultas** (`/appointments`): Endpoint para cadastrar, editar, listar e deletar consultas. As consultas são vinculadas aos pacientes.

**Gerenciamento de Exames** (`/exams`): Endpoint para cadastrar, editar, listar e deletar exames. Os exames também são vinculados aos pacientes.

**Dashboard** (`/dashboard/stats`): Endpoint para obter estatísticas gerais sobre o número de pacientes, consultas e exames cadastrados no sistema.

**Prontuários** (`/patients/{id}/medical-record` e `/patients/medical-record-list`): Endpoints para consultar o prontuário de uma paciente específico, incluindo todos os exames e consultas vinculadas a ele e endpoint para listar todos os pacientes, incluindo Id, nome e plano de saúde do paciente.


## Como testar

1. Faça o clone do projeto para sua máquina.
2. Certifique-se de ter o PostgreSQL, pgAdmin 4 e Insomnia instalados. Se não tiver, você precisará instalá-los.
3. Certifique-se de ter uma IDE com suporte ao Java. Sugestão: IntelliJ IDEA.
4. Abra o projeto na sua IDE.
5. Inicie o pgAdmin 4.
6. Crie um banco de dados no PostgreSQL com o nome `labmedical`.
7. Configure o arquivo `application.properties` do projeto de acordo com as credenciais do seu ambiente:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/labmedical
spring.datasource.username=postgres
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
```
8. Inicie o Insomnia e importe o arquivo `Insomnia_2024-08-10.json` que está na pasta raiz do projeto.
9. Na sua IDE, execute a classe `LaBmedicalApplication` para iniciar a aplicação.
10. Teste os endpoints através do Insomnia.

## Ferramentas e Dependências do Projeto

Este projeto foi construído usando as seguintes tecnologias e bibliotecas:

**Ferramentas de desenvolvimento:**

- IDE: IntelliJ IDEA
- Banco de Dados: PostgreSQL
- Gerenciamento de Banco de Dados: pgAdmin 4
- Testes de API: Insomnia

**Dependências do Maven:**

- spring-boot-starter-data-jpa
- spring-boot-starter-oauth2-resource-server
- spring-boot-starter-security
- spring-boot-starter-validation
- spring-boot-starter-web
- postgresql
- spring-boot-starter-test
- spring-security-test
- jjwt-api
- slf4j-api
- slf4j-simple


 
