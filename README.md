# Documentação Projeto Conefer

### Motivações

O intuíto desta documentação é para as evoluções da ferramenta Conefer, e com ela que nos guiará para futuras versões e evoluções do produto.
Com isto, ao passo que formos evoluindo como ferramenta iremos alimentar esta para sempre se manter atualizada.

Principais benefícios de manter atualizado esta documentação é que será sempre o nosso guia para a orientação do Time **Conefer**.

### Padrão dos endpoints da arquitetura (rotas) dos recursos

Padrão plural: Utilizaremos este padrão para definir as rotas.

**Exemplos de requisições**

|          Requisição | Path    | Objetivo                                                |
| -------------:|:--------:| ----------------------------------------------------------|
|     `GET` | /api/produtos  | Consultar todos os produtos|
|    `GET` | /api/produtos/1 | Consultar produtos específico|
|    `POST` | /api/produtos  | Criar um produto|
|    `PUT` | /api/produtos/1 | Atualizar um produto específico |
|    `DELETE` | /api/produtos/2020 | Deletar um produto específico|

_**Boas práticas**:_

1. Nomes compostos para os endpoints sera utilizado o " - ". **Ex.: grupos-documentos, lista-page, documentos-template**

2. Para paths com muitos parametros utilizar o @QueryParam

3. Paginação: Em requisições GET que retornem listas, devolva somente o necessário, pagine sua requisição, desta forma todos ganham, pois será trafegado somente o necessário, melhorando a experiencia do usuário final.
   Não existe nenhum padrão amplamente utilizado pelo mercado para tal prática, por esse motivo sugiro que sua utilização esteja bem explicita para os usuários, por exemplo: http://localhost:8080/api/clientes?start=20&limit=5

4. Ordenação : Otimize suas requisições, facilite ainda mais a vida de seus usuários, permita que sua Api, ordene sua resposta, desta forma o usuário pode não precisar realizar nenhum tipo de tratamento no dado.

_**Evitar**:_

    1. Paths extensos : Evite Paths enormes, limite até no máximo 2 níveis de Paths aninhados ao principal. Ex: /api/produtos/1/modalidade/COMBUSTIVEL

### Padronização dos códigos de resposta

###### HTTP 200 - OK

Geralmente esse é um código de retorno é utilizado como coringa, onde independente do tipo de requisição, se o processamento é realizado com sucesso, esse é o código de retorno default, porem eu recomendo utiliza-lo nos seguintes momentos:

    Requisições do tipo GET onde o dado de retorno não esta paginado, por exemplo uma consulta por um id.
    Requisições GET onde a API esta retornando a ultima pagina de um dado paginado.
    Requisições POST que realizem operações lógicas que não criam dados, por exemplo um cálculo.

###### HTTP 201- Created

Geralmente utilizado em requisições POST, onde algum dado foi criado em algum repositório.

###### HTTP 202 - Accepted

Geralmente utilizado em requisições POST, indica que algum dado foi recebido e seu processamento será realizado de forma assíncrona , por exemplo a API posta o dado em uma fila MQ e um worker tratará o dado em um segundo momento.

###### HTTP 400 - Bad Request

Geralmente utilizado em todos os tipos de requisições, ocorre quando o cliente preencheu algum dado incorreto na requisição.

Geralmente este é um código coringa, quando sua API não tratar individualmente outros códigos 4XX, a mesma deve retornar o código 400.

###### HTTP 401 - Unauthorized

Geralmente é utilizado para qualquer tipo de requisição, ocorre sempre que um usuário não esta autenticado para uso da API.

###### HTTP 404 - Not Found

Esse código de resposta pode ser utilizado nos seguintes momentos:

    Pode ser utilizado para qualquer tipo de requisição, ocorre quando a aplicação cliente procura por uma rota/recurso que não existe
    Pode ser utilizado em uma requisição GET ou PUT, ocorre quando a aplicação client tenta consultar ou alterar um domínio que não existe

###### HTTP 408 - Timeout

Geralmente é utilizado para qualquer tipo de requisição, ocorre sempre que uma API gera timeout em uma das suas operações.

###### HTTP 500 - Internal Server Error

Geralmente é utilizado para qualquer tipo de requisição, ocorre para qualquer tipo de erro de processamento da API.
Geralmente este é um código coringa, quando sua API não tratar individualmente outros códigos 5XX, a mesma deve retornar o código 500.

### Versionamento da API

Para versionamento da api utilizamos o seguinte padrão:

|          Requisição | Path    | Objetivo                                                |
| -------------:|:--------:| ----------------------------------------------------------|
|     `GET` | /api/v1/produtos  | Consultar todos os produtos|
|    `GET` | /api/v2/produtos/1 | Consultar produtos específico|
|    `GET` | /api/v3/produtos/1 | Consultar produtos específico|

_**Evitar:**_

Nunca modificar um método e ou DTO (Data Transfer Object) que já esteja em produção para os aplicativos, pois isto pode impactar no funcionamento do mesmo. Sempre consultar o project owner para que esta mudança seja realizada

### Padrão de nomenclatura do projeto

###### Nomes de Classes

**Para nomearmos uma classe em Java devemos seguir as seguintes regras:**

1. Toda classe deve começar com letra Maiúscula.
2. Não deve possuir caracteres com acento (ç, á, î, ã, Á, À).
3. Não deve possuir caracteres especias (@, !, %, &).
4. Organizar tipos dos objetos: Ex.: String, Long, BigDecimal, boolean,  Relacionamentos com entidades e mapeamentos, List, Set e assim por diante.
5. Sempre Utilizar o lombook para anotar os getters e setters nas classes quando necessário.
6. Caso um classe possua um nome composto, a primeira letra de cada palavra deverá começar com letra maiúscula, Ex.: PrimeiraClasse, MinhaClasse, OlaMundo

###### Nomes de Pacotes

1. Nomes de pacotes devem começar com a primeira letra em minúscula. Jamais devemos iniciar o nome de um pacote com caracteres especiais (@, #, $, %, &, *, _, etc…) ou um número.
2. Caso o nome de um pacote seja composto por mais de uma palavra, a primeira letra da segunda palavra e das palavras posteriores deve ser em maiúscula.: modelo, controle, conexaoDeBancoDeDados
3. Os pacotes deverão respeitar o nome .: com.ifg1.manutencao.coneferserver

###### Nomes de Variáveis

Variáveis podem começar com qualquer letra minúscula (sem acento) e os caracteres $ ou _, porém não podem começar com números.
Caso o nome de um pacote seja composto por mais de uma palavra, a primeira letra da segunda palavra e das palavras posteriores deve ser em maiúscula. Ex.: _x, $a, b1, c, calculadoraCientifica

###### Nomes de Métodos

1. Métodos (funções) podem começar com qualquer letra minúscula, não podem possuir acentos e não podem começar com números.
2. Caso o nome de um método seja composto por mais de uma palavra, a primeira letra da segunda palavra e das palavras posteriores deve ser em maiúscula. Ex.: imprimir(), imprimirTela(), calcularMedia()
3. Métodos que retornem apenas um objeto sempre utilizar o prefixo _**Consultar**_. Ex: consultarUsuario();
4. Métodos que retornem uma lista sempre utilizar o prefixo _**Listar**_. Ex: listarUsuario();
5. Evitar comentários no método, sempre que pensar em utilizar um comentário sempre melhore o nome do método para que a leitura seja mais hábil para o usuário;
6. Evitar métodos com mais de 30 linhas. Um método deve ter apenas uma responsabilidade, se este possui métodos que tem várias responsabilidades quebrar estes em diversos métodos

###### Nomes de Constantes

Constantes devem ser escritos em letras maiúsculas. Usamos o underline (“_”) para separar nomes compostos. Ex: TAMANHO, PARAR_DE_EXECUTAR, PI, TESTE_JOGO

###### Alinhamento do código

Inicie com 4 espaços ou um tab. A construção exata do recuo (espaços vs tabs)é indeterminado. Tabs deve ser definido exatamente cada 8 espaços (não 4). Evite linhas com mais de 130 caracteres, uma vez que em alguns terminais fica mais difícil de ler.

Quando uma expressão não couber em uma única linha, quebrá-lo de acordo com estes princípios gerais:

    a) Quebre após uma vírgula.
    b) Quebre antes de um operador.
    c) Prefira quebras de nível superior para diminuir o nível-breaks.
    d) Se as regras acima expostas levarem à um código confuso ou ao código que está esmagado contra a margem direita, apenas coloque 8 espaços em seu lugar.

Ex.:

```java
//EX. 1
        someMethod(longExpression1, longExpression2, longExpression3,
        longExpression4, longExpression5);

//EX. 2
        var = someMethod1(longExpression1,
        someMethod2(longExpression2,
        longExpression3));

//EX. 3
        if ((condition1 && condition2)
        || (condition3 && condition4)
        ||!(condition5 && condition6)) {
        doSomethingAboutIt();
        }

//EX. 4
        if ((condition1 && condition2) || (condition3 && condition4)
        ||!(condition5 && condition6)) {
        doSomethingAboutIt();
        }

//EX. 5        
        alpha = (aLongBooleanExpression) ? beta : gamma;

       alpha = (aLongBooleanExpression) ? beta
       : gamma;

       alpha = (aLongBooleanExpression)
       ? beta
       : gamma;
```

### Padrão MVC


### Padrão de Envios e Retornos com os clients integrados a API (DTO - Data Transfer Object)

O próprio nome já diz muito: um objeto simples usado para transferir dados de um local a outro na aplicação, sem lógica de negócios em seus objetos
e comumente associado à transferência de dados entre uma camada de visão (view layer) e outra de persistência dos dados (model layer)



As classes DTO's são classes que são utilizadas para o trafégo de informações entre os clients (apps, painel, integrações) entre os sistemas, e estes
sempre devem ter o mínimo de campos possíveis, sempre prezar pelo menor tráfego de informações possíveis.

Nesta arquitetura tambem evitamos trafegar os DTO's pelas classes que não são de controles, e com isto evitamos de poluição do código com camadas de visão na API.

Para separação, ao invés de utilizar apenas o sufixo DTO, as classes serão separadas em ResponseModel e RequestModel, no pacote model e subpacotes response e request 
devendo estes sufixos serem utilizados para facilitar a leitura nas demais classes. 

Segue alguma das especificações para esta classe:

```java
/**
 * Anotar as entidade com os argumentos do lombook (não gerar getters e setters manualmente exceto em caso de necessidade)
 * Sempre que possível anotar com todos estes
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DocumentoResponse {

    /**
     * Sempre anotar o identificador do objeto (entidade) com o uuid deste. OBS: NUNCA utilizar o ID da tabela no banco para utilizarmos em parsers.
     * Atualmente possuímos diversos, mais sempre que possível (para painel e integrações) atualizar para este novo padrão
     */
    private String uuid;

    /***
     * Para campos numericos decimais utilizar sempre como BigDecimal
     */
    private BigDecimal longitude;

    /**
     * Manter sempre as variáveis com a mesma tipagem juntas e claro com ordem alfabetica (sempre que for possível)
     */

    private String cidade;

    /**
     * Para enuns vamos sempre adotar o padrão de envio e retorno com o tipo String
     */

    private String modalidadeExibirDescontoValor;

    /**
     * Para campos inteiros não decimais utilizar sempre Long
     */
    private Long ordem;

    /**
     * Para campos boolean sempre utilizar o tipo primitivo pois evitar nullpointer em determinadas situações. Claro que dependendo do 
     * contexto poderá ser utilizado não primitivo.
     */
    private boolean utilizado;

    /**
     * Para campos de data vamos adotar o padrão de utilizar o campo long. Para evitar possíveis mudanças e facilitar a manutenção
     */
    private long dataInicial;
}
```


###### Padrão das Entidades (Modelos integrados ao Hibernate)



###### Padrão dos Controllers (RestController)

//DESCREVER OS PADRÕES

###### Padrão dos Serviços (Services)

//DESCREVER OS PADRÕES

###### Padrão dos Repositories (Repositoy)

//DESCREVER OS PADRÕES

###### Padrão dos Testes Unitários (Repositoy)

//DESCREVER OS PADRÕES

# Configurações do projeto


#Variáveis de Ambiente (VM Args)
1. Copiar o texto abaixo para a área de transferência;
2. Clicar no SELECT (DropBox) do lado esquerdo superior do InteliJ; 
3. Ir em Edit Configurations;
4. No campo "VM Options" colar o conteúdo da área de transferência;
5-Atente-se que, caso tenha uma senha ou usuário diferente para o seu banco local, deve trocar conforme 
for o caso, abaixo está como default.

```
-Dspring.profiles.active=dev
-Dconefer.datasource.url=jdbc:postgresql://localhost:5432/conefer
-Dconefer.datasource.username=postgres
-Dconefer.datasource.password=123456
```

###### Versões e Tecnologias

1. Java : 17
2. Springboot: 2.6.1
3. Servidor de Aplicação: Provided (Servidor próprio do spring boot)
4. Banco de dados: Postgres 13
5. Documentação Swagger (http://localhost:8080/conefer-server/swagger-ui.html#/)

### Fontes

1. https://medium.com/@wssilva.willian/design-de-api-rest-9807a5b16c9f
2. https://www.devmedia.com.br/convencoes-de-codigo-java/23871