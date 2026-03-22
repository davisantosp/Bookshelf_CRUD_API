<h1>Bookshelf API CRUD 📚</h1>
API desenvolvida em <b>Java/Spring</b>, utilizando <b>PostgreSQL</b> e <b>Docker</b> para padronizar o ambiente de desenvolvimento.
O objetivo do projeto é trabalhar conceitos básicos de <b>APIs REST</b>, <b>DTOs</b>, <b>Migrations com Flyway</b> e <b>testes unitários</b> com <b>JUnit</b> e <b>Mockito</b>.
<hr>

<h2>Principais tecnologias e ferramentas</h2>

### **Core da API**
* **Java 21**
* **Spring Boot 3.4.4**
* **Spring Data JPA**
* **Lombok**
  
### **Banco de Dados**
* **PostgreSQL**
* **Flyway**

### **Testes**
* **JUnit 5**
* **Mockito**

### **Infraestrutura & DevOps**
* **Docker & Docker Compose**
* **Maven**

<br>
<hr>

<h2>Como executar o projeto</h2>

1.  **Clone o repositório do GitHub:**
    ```bash
    git clone https://github.com/davisantosp/Bookshelf_CRUD_API
    cd Bookshelf_CRUD_API
    ```

2.  **Suba os containers:**
    ```bash
    docker-compose up --build
    ```
    *Este comando irá baixar as imagens, compilar o código Java, rodar as migrações do Flyway e subir a API na porta `8080`.*

3.  **Acesse a API:**
    A base URL será `http://localhost:8080/books`

<br>
<hr>
Desenvolvido por Davi dos S. Pinto - Linkedin: https://www.linkedin.com/in/davi-dos-santos-pinto-b165a3336/
