# ✅ Projeto Todo List — Spring Boot + Frontend

Este projeto é uma aplicação fullstack simples de gerenciamento de tarefas (Todo List), desenvolvida com:

- Backend em Spring Boot
- API REST
- Banco local persistente
- Frontend com HTML + JavaScript puro
- Integração via fetch API

O sistema permite criar, listar, atualizar e remover tarefas com prioridade e status de conclusão.

---

# 🚀 Funcionalidades

✅ Criar tarefas  
✅ Listar tarefas ordenadas por prioridade  
✅ Marcar tarefa como realizada  
✅ Atualizar status das tarefas  
✅ Deletar tarefas  
✅ Validação de inputs obrigatórios  
✅ Banco persistido localmente (`./data`)  
✅ Frontend separado do backend  

---

# 🧱 Tecnologias usadas

## Backend
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Banco local (arquivo persistente)
- Maven Wrapper

## Frontend
- HTML
- CSS
- JavaScript (Vanilla)
- Fetch API

---

# 📂 Estrutura do projeto
```
segundoSpring/
│
├── src/main/java/        → código backend
├── src/main/resources/
│   ├── static/           → frontend (html, css, js)
│   └── application.properties
│
├── data/                 → banco local gerado automaticamente
├── mvnw
├── mvnw.cmd
└── pom.xml
```


---

# ⚙️ Configuração do Banco Local

O banco é armazenado localmente na pasta `./data`.

## application.properties

```properties
spring.application.name=segundoSpring

spring.datasource.url=jdbc:h2:file:./data/todos
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
```

