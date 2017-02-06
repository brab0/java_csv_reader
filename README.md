#Java Csv Reader
Parser de arquivos CSV para Datatable com execução de queries através do console.


##Config
- git clone url 
- import > existing maven projects
- maven > update project
- run as > maven build > clean install package

##Run
###Tests
- O maven build já irá rodar todos os test cases, caso a opção skip tests estiver desmarcada. Porém,  possível executar a classe TestSuite para rodar todos (Run as > JUnit Test).

###Application
- Run as > Java Application. O console estará disponível para entradas.
- Em "src/test/resources/" existem alguns arquivos sample. Selecione o arquivo a ser usado executando no console: use src/test/resources/cidades.csv
- Escreva suas consultas. 


####Considerações
- O programa utiliza a primeira linha do arquivo lido como header do Datatable. Sendo assim, os itens deste header são utilizados como propriades(colunas) para as queries.
- Para paths ou arquivos com espaço, pode-se utilizar aspas duplas("arquivo com espaço.csv")


##Console
- **use path/to/file [delimiter encodeType]** - lê csv e seta o datatable que será utilizado para consultas. Os parâmetros delimiter e encodeType são opcionais e seus respectivos valores padrão são "," e "UTF-8".
- **clear** - simula limpeza do console.
- **exit** - sai da aplicação.
- **count** * - escreve no console a contagem total de registros importados
- **count distinct propriedade** - escreve no console o total de valores distintos da propriedade (coluna) enviada 
- **filter propriedade valor** - escreve no console a linha de cabeçalho e todas as linhas em que a propriedade enviada possua o valor enviado 


##Consideraçes sobre o Projeto
- Cobertura de testes unitários
- Utilização de conceitos do padrão de projetos Adapter para evolução e manuteção da classe Operations.
- Flexibilidade do código para futuras evoluções. Abstração voltada para datatables que possam receber qualquer tipo de arquivo csv.
