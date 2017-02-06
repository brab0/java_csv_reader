#Java Csv Reader
-------------
Parser de arquivos CSV para Datatable com execução de queries através do console.
-------------


##Configuração
-------------
- git clone url 
- import > existing maven projects
- maven > update project
- run as > maven build > clean install package


##Console Commands
-------------
- **use path/to/file [delimiter encodeType]** - lê csv e seta o datatable que será utilizado para consultas.
- **clear** - simula limpeza do console.
- **exit** - sai da aplicação.
- **[queries]** - Utilizando as queries abaixo, é possível consultar no datatable selecionado.


##Queries
-------------
- **count** * - escreve no console a contagem total de registros importados
- **count distinct propriedade** - escreve no console o total de valores distintos da propriedade (coluna) enviada 
- **filter propriedade valor** - escreve no console a linha de cabeçalho e todas as linhas em que a propriedade enviada possua o valor enviado 


##Consideraçes sobre o Projeto
-------------
- Cobertura de testes unitários
- Utilização de conceitos do padrão de projetos Adapter.
- Flexibilidade do código para futuras evoluções. Abstração voltada para datatables que possam receber qualquer tipo de arquivo csv.
