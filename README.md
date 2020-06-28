# DesafioCielo
Api criada baseada no sistema legado disponibilizado via e-mail

##Aquitetura
Arquitetura utilizada foi MVC

###Entidades
   * Banco - dados bancários
   * Remessa - dados da remessa
   * Lancamento - Pagamento das remessas com aos dados bancários

##Frameworks/Libs/Ferramentas
   * [Spring Boot](https://www.springbot.com/)
   * [Hibernate](https://hibernate.org/)
   * [Docker](https://docs.docker.com/)
   * [Liquibase](https://www.liquibase.org/)
   * [Gradle](https://gradle.org/)
   * [Lombok](https://projectlombok.org/)
   * [PostgreSQL](https://www.postgresql.org/)
   * [JacksonJSON](https://www.baeldung.com/jackson/)

##Como Usar
    Para utilizar a api:
        * com docker:
            * git clone https://github.com/adrmarciao/DesafioCielo.git
            * Acessar pagina do projeto
            * Com o docker instalado executar docker-compose up
        * sem docker:
            * instalar postgres
            * criar um banco de dados DB
            * importar o projeto no intellij
            * executar
            
##Variáveis de ambiente
   * POSTGRES_DATABASE
   * POSTGRES_DB
   * POSTGRES_USER
   * POSTGRES_PASSWORD

##API REST
 * Banco
    * /api/banco/ [PUT/POST]
    * /api/banco/{id} [GET]
    * /api/banco/{page}/{limit} [GET]
    * /api/banco/{id} [DELETE]
 * Lancamento
    * /api/lancamento/ [PUT/POST]
    * /api/lancamento/{id} [GET]
    * /api/lancamento/{page}/{limit} [GET]
    * /api/lancamento/{id} [DELETE]
 * Remessa
     * /api/remessa/ [PUT/POST]
     * /api/remessa/{id} [GET]
     * /api/remessa/{page}/{limit} [GET]
     * /api/remessa/{id} [DELETE]
    
   
