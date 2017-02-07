#Java Csv Reader
Parser de arquivos CSV para Datatable com execução de queries através do console da IDE(Eclipse).


##Considerações sobre o Projeto
- O projeto utiliza Maven e JUnit;
- Tem cobertura de testes unitários para as principais classes e métodos.
- Foram utilização alguns conceitos do padrão de projetos Adapter para evolução e manutenção da classe Operations.
- Abstração de Datatable: o arquivo sample (src/test/resources/cidades.csv) poderia ser facilmente abstraído para um modelo que representasse ou extendesse específicamente seu conteúdo (estados->cidades->region->etc). Porém, a modelagem em forma de Datatable permite, não só uma escalabilidade maior, no sentido de aceitação de novos tipos e formatos de fontes e operações, como também evita processamentos desnecessários(para a problemática em questão) no que diz respeito a consulta e retorno.


##Config
- git clone url 
- import > existing maven projects
- maven > update project
- run as > maven build > clean install package


##Run
###Tests
- O maven build já irá rodar todos os test cases, caso a opção skip tests estiver desmarcada. Porém, é possível executar a classe TestSuite para rodar todos testes de uma vez só ou um por um (Run as > JUnit Test).

###Application
- Run as > Java Application. O console estará disponível para entradas.
- Em "src/test/resources/" existem alguns arquivos sample. Selecione o arquivo a ser usado executando no console: use src/test/resources/cidades.csv
- Escreva suas consultas. 


##Console
- **use path/to/file [delimiter encodeType]** - lê csv e seta o datatable que será utilizado para consultas. Os parâmetros delimiter e encodeType são opcionais e seus respectivos valores padrão são "," e "UTF-8".
- **clear** - simula limpeza do console.
- **exit** - sai da aplicação.
- **count** * - escreve no console a contagem total de registros importados
- **count distinct propriedade** - escreve no console o total de valores distintos da propriedade (coluna) enviada 
- **filter propriedade valor** - escreve no console a linha de cabeçalho e todas as linhas em que a propriedade enviada possua o valor enviado 

**Observações**
- Para queries que utilzem valores com espaços em branco, estes valores deverão estar entre aspas duplas ("valor com espaço em branco").
- O programa utiliza a primeira linha do arquivo lido como header do Datatable. Sendo assim, os itens deste header são utilizados como propriades(colunas) para as queries.
- Para paths ou arquivos com espaço, pode-se utilizar aspas duplas("arquivo com espaço.csv")
