#Java Csv Reader
Parser de arquivos CSV para Datatable com execução de queries através do console da IDE(Eclipse).


##Considerações sobre o Projeto
- O projeto utiliza **Maven e JUnit**;
- Tem **cobertura de testes unitários** para as principais classes e métodos.
- Foram utilização alguns conceitos do **padrão de projetos Adapter** para evolução e manutenção da classe Operations.
- Abstração de **Datatable**: o arquivo sample (src/test/resources/cidades.csv) poderia ser facilmente abstraído para um modelo que representasse ou extendesse específicamente seu conteúdo (estados->cidades->region->etc). Porém, a modelagem em forma de Datatable permite, não só uma escalabilidade maior, no sentido de aceitação de novos tipos e formatos de fontes, como também evita processamentos desnecessários (para a problemática em questão) no que diz respeito a consulta e seu retorno.
- Optou-se por **escolher o arquivo a ser lido** somente após a abertura do console (run). Desta forma, pode-se realizar **testes exploratórios** de forma mais eficiente (e legal) e versátil, visto a possibilidade da leitura de outros tipos de fontes. 


##Config
- git clone https://github.com/brab0/java_csv_reader 
- import > existing maven projects
- maven > update project
- run as > maven build > clean install package


##Run
###Tests
- O maven build já irá rodar todos os test cases, caso a opção skip tests estiver desmarcada. Porém, é possível executar a classe TestSuite para rodar todos testes de uma vez só ou um por um (Run as > JUnit Test).

###Application
- **Run as > Java Application**. O console estará disponível para entradas.
- Em "src/test/resources/" existem alguns arquivos sample. Selecione o arquivo a ser usado executando no console: **use src/test/resources/cidades.csv**
- Escreva suas consultas. 


##Console
- **use path/to/file [delimiter encodeType]** - lê csv e seta o datatable que será utilizado para consultas. Os parâmetros delimiter e encodeType são opcionais e seus respectivos valores padrão são "," e "UTF-8".
- **clear** - simula limpeza do console.
- **exit** - sai da aplicação.
- **count** * - escreve no console a contagem total de registros importados
- **count distinct propriedade** - escreve no console o total de valores distintos da propriedade (coluna) enviada 
- **filter propriedade valor** - escreve no console a linha de cabeçalho e todas as linhas em que a propriedade enviada possua o valor enviado 

**Observações**
- As queries são case insensitive.
- O algorítimo irá reponder somente às operações e propriedades existentes, mas não necessariamente irá exibir exceções para todos o casos. Isto permite que a sintaxe seja menos imperativa e as sentenças possam ser escritas de forma mais semântica (ex: **filter** cities and get only when **uf** is equal **sc**).
- As queries seguem um encadeamento em cascata guiado por prioridades (count por último, ex). Isso quer dizer que as sentenças podem ser misturadas, mesmo que em alguns casos isso não faça tanto sentido (ex: **distinct uf** and **filter** where value is **PA**).
- Para valores passados com parâmetro em queries, paths ou arquivos contendo espaços em branco, estes deverão estar entre aspas duplas (ex: filter name "rio de janeiro").
- O programa utiliza a primeira linha do arquivo lido como header do Datatable. Sendo assim, os itens deste header são utilizados como propriades(colunas) para as queries.
