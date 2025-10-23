O projeto consiste no desenvolvimento de uma aplicação fullstack (Java/Angular) para o gerenciamento de Pontos Turísticos, Países e Comentários. A arquitetura backend foi concebida para seguir o padrão de três camadas, utilizando o ecossistema Spring Boot para serviços RESTful e persistência, e o frontend Angular com o Design System PO-UI.

O backend foi desenvolvido em Java e estruturado nas seguintes camadas:

a) Camada de Domínio e Persistência (JPA/Hibernate)
O projeto utilizou o Spring Data JPA como Object-Relational Mapping (ORM), com Hibernate como provedor, para gerenciar o estado das classes de domínio no banco de dados.
Entidades: As classes País, PontoTuristico e Comentário foram definidas como entidades de persistência através da anotação @Entity.
Relacionamentos: Foi empregado o mapeamento relacional @ManyToOne (entre Ponto Turístico e País, e Comentário e Ponto Turístico) para garantir a integridade dos dados e a navegação entre as entidades.
Otimização: Utilizou-se FetchType.LAZY para evitar o carregamento desnecessário de dados relacionados (N+1 problem) e anotações como @JoinColumn(nullable = false) para impor a obrigatoriedade da chave estrangeira.
Validação: Uso do Bean Validation (@NotNull, @NotBlank) em DTOs de requisição para garantir a integridade dos dados antes da persistência.

b) Camada de Serviço (Service)
Esta camada implementou a lógica de negócio, orquestrando as operações do CRUD:
DTO (Data Transfer Objects): Uso de padrões Request (entrada de dados) e Response (saída de dados) para desacoplar a camada de Controller da camada de Domínio, assegurando que apenas os dados necessários sejam transmitidos.
Transacionalidade: Uso da anotação @Transactional para garantir a atomicidade das operações de persistência e @Transactional(readOnly = true) para otimizar operações de leitura.

c) Camada de Controller (API REST)
Os recursos foram expostos seguindo os princípios RESTful:
Endpoints Aninhados: Utilização de rotas aninhadas (ex: /api/pontos-turisticos/{id}/comentarios) para modelar a relação hierárquica entre as entidades.
Status HTTP: Retorno de códigos de status HTTP semânticos (201 Created, 204 No Content, 404 Not Found) via ResponseEntity.

O frontend utilizou Angular para a construção da Single Page Application (SPA) e o PO-UI como Design System:
Componentização: Utilização de componentes específicos para Listagem (po-table), Cadastro/Edição (po-page-default) e Detalhe (po-page-default).
Formulário Reativo: Gerenciamento do estado e validação do formulário de Cadastro/Edição de Ponto Turístico utilizando ReactiveFormsModule.
Serviços Tipados: Criação de services Angular dedicados para cada recurso (ponto-turistico.service.ts), utilizando HttpClient e tipagem TypeScript para garantir a comunicação fiel aos DTOs do Java.

A aplicação fullstack encontrou dificuldades para iniciar e mapear corretamente os Controllers, resultando em erros HTTP 404 (Not Found) no Front-end (o servidor estava ativo, mas as rotas não).
O problema principal foi a falha na inicialização do Contexto de Aplicação do Spring (ApplicationContextException), decorrente de problemas na injeção de dependência (UnsatisfiedDependencyException), que travava o servidor.
A causa subjacente da falha 404 no Front-end era o erro de startup do Back-end impedindo o Component Scan de mapear as rotas (/api/pontos-turisticos), demonstrando a interdependência crítica entre a fase de inicialização do Spring e a disponibilidade da API.
