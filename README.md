# Projecto-AEDII
Music Streaming Information System

1.Descrição do Problema

Pretende-se, neste projeto, simular parcialmente as funcionalidades dum serviço online de informação  de músicasem  streaming.
A  aplicação irá gerir tabelas  de símbolos(symbol  tables -ST) de:
utilizadores,  músicas,artistas, géneros  musicaise  playlists. 
Além disso,as músicas podem ser agrupadas por: género musical, playlist, artista, e por histórico  de  audição  de  um  utilizador.
Estes  agrupamentos  serão  modelizados  por tabelas  de  símbolos  de  elementos  em  que  cada  valor  
poderá  ser  outra  tabela  de símbolos.

2.Requisitos Funcionais

Pretende-se que desenvolva uma API de funções (biblioteca / conjunto de funções) que satisfaçam  os  requisitos listados  a 
seguir e ainda  casos  de  teste  dessa  API  (funções  de teste).
Note que  não  é  requisito  do  projeto  o  desenvolvimento  uma  interface  de utilizador  completamente  funcional. 
Descrevem-se  a  seguir  osrequisitos a  cumprir neste projeto de programação:

R1 [Feito] .Criar modelos de dados para os valores(classe genérica java Value das STs) dos elementos das várias tabelas de símbolos consideradas no projeto.

R2 [Feito].Usar  funções  e  tabelas  de  hash em,  pelo  menos,  duas  STs  cuja  chave  não  é ordenável (eg:utilizadores e artistas).

R3 [Feito].Usar  BSTs  balanceadas  (redblack)  em,  pelo  menos,  as  STs cuja  chave  é ordenável como tempos ou ordem.

R4 [WIP-80%].Criar  funções  para  inserir,  remover,  editar  e  listar  tudo,  para  as  várias  STs consideradas: utilizadores,   músicas,   artistas,   géneros   musicais   e   playlists presentes na base de dados.

R5 [Feito].Validar  que  todas  as  músicas que  são  consideradas  noutras  STs  existem  na  ST músicas.

R6 [WIP-65%].Implementar várias pesquisa à base de informação como, por exemplo:
quais as playlists  em  que  aparece  uma  dada  música;  qual  é a música  mais  popular  nas diferentes  playlists;  qual  o  artista  e  género de  música  foram  mais  ouvidos  num 
dado dia ou intervalo de tempo, entreoutras pesquisas.

R7 [Feito].Remover músicas  da  base  de  dados  com  validação  da sua  remoção  total  do sistema.

R8.Popular  as  diversas  STs  da  aplicação  com  o  conteúdo  de  ficheiros  de  texto  de 
entrada  (restore/ load da  informação  em  ficheiro).  Gerar  dados  sintéticos  de teste na própria aplicação (eg: gerar utilizadores fictícios) para popular as várias STs.

R9.Fazer  o  output (dump)  de  toda  a  informação  para  ficheiros  de  textos,  isto  é, de utilizadores, artistas, músicas, playlists e de todas as outras STs.

R10.Correr casos de  teste  (funções  de  teste)  para  todas  as  funcionalidades implementadas nos  requisitos  anteriores,  considerando,  para  o  efeito,  uma  ou 
várias   classes   de   teste   específicas.
Deverá   implementar   mecanismos   / instrumentos  de  medição  temporal  da  execução  das  principais  funções  de inserção
e  pesquisa  nesses  testes.  Esses  cenários  deverão  incluir  nos  testes  a leitura  e  escrita  de  ficheiros  com  dados  de  input  (batch  inputdata)  e/ou
resultados  de  output.  Os  resultados  dos  testes  deverão  ser  escritos  para  ficheiro de texto. Para cada teste realizado deverá ser explicitada (no ficheiro de texto) a função testada e o resultado do teste.
