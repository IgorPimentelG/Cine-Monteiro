# Projeto Da Disciplina Programação II - Cine Monteiro

## 1. Descrição do Projeto 
O Sistema deve permitir que um administrador cadastre salas onde serão feitas as exibições de filmes. Ao cadastrar uma sala, o administrador vai informar 
a quantidade de acentos que aquela sala tem. O administrador também vai cadastrar filmes que serão exibidos pelo cinema. Um mesmo filme pode ser exibido
em mais de uma sala. Ao alocar um filme em uma sala, deve-se informar os horários em que o filme será exibido (as sessões) e por qual período. O sistema
deve ser capaz de impedir que aconteçam conflitos na definição dos horários de exibição do filme (tratar sessões de filmes que possuem intersecção de horário).
Os clientes do cinema podem comprar ingressos para sessões. O sistema deve ser capaz de emitir alguns relatórios gerenciais, como o rendimento que um filme está 
tendo ou o rendimento de uma sessão específica. O sistema também, automaticamente, parar de vender ingressos para filmes que estão fora do período de exibição.

## 2. Requisitos Funcionais
1. <b>Cadastrar administrador</b> - como o administrador é um usuário especial e único no sistema, quando o programa for iniciado pela primeira vez, 
   se não houver nenhum administrador cadastrado a única funcionalidade disponível deve ser a de cadastro de administrador. O sistema deve registrar, 
   pelo menos, um login (e-mail) e uma senha para o administrador.

2. <b>Realizar login</b> – o usuário deve se autenticar no sistema para utilizar as funcionalidades. Se o login for realizado pelo administrador, ele deve 
   ser direcionado para tela de administração. Se o login for realizado por um cliente, ele deve ser direcionado para uma tela com a lista de sessões em 
   exibição do filme.

3. <b>Recuperar senha</b> – o sistema deve ter uma opção de recuperar a senha de um usuário por e-mail. Nesse caso, o usuário recebe uma senha provisória e, 
   no primeiro login com essa senha provisório, é requerido que ele faça uma atualização da senha.

4. <b>Cadastrar sala</b> – o administrador vai poder cadastrar salas onde acontecerão as exibições. O cadastro da sala deve exigir: um nome, um total de acentos
   e o valor do ingresso para aquela sala.

5. <b>Excluir sala</b> – o administrador pode excluir salas do sistema, apenas se não houver nenhuma sessão ativa para aquela sala. Além disso, as salas excluídas
   devem continuar aparecendo nos relatórios do sistema, mas com alguma indicação que permita ao administrador reconhecer que aquela sala foi deletada. 

6. <b>Listar as salas</b> – o administrador deve poder visualizar todas as salas cadastradas em uma tabela.

7. <b>Detalhar sala</b> – na tela de listagem, o administrador pode escolher detalhar uma sala. Nesse caso, ele é direcionado para outra tela, onde poderá ver 
   todas as informações sobre a sala selecionada, tais como: a lista dos filmes que já foram exibidos naquela sala, o total de pessoas que já frequentou a sala, 
   a quantidade de acentos, o nome da sala, as sessões que a sala tem ativa, quanto dinheiro essa sala arrecadou desde a sua criação, quanto de dinheiro essa sala
   arrecadou nos últimos 7 dias, etc.

8. <b>Cadastrar filme</b> – o administrador cadastra filmes para serem exibidos; o cadastro do filme inclui a definição da duração do filme, classificação etária
   e informações sobre o filme (nome, sinopse, gênero, etc.), além do período em que ele continuará em cartaz.

9. <b>Listar todos os filmes</b> – os usuários do sistema devem poder ver todos os filmes que já foram exibidos no cinema. Deve haver uma opção de filtro que 
   apresenta apenas os filmes com sessões de exibição ativa.

10. <b>Cadastrar sessão</b> – após o filme ser cadastrado, o administrador poderá cadastrar sessões onde ocorrerá a exibição do filme. A seção deve ter uma 
    hora de início e uma sala dentre as cadastradas. O horário do fim da sessão é calculado automaticamente, com base no tempo de duração do filme. 
    Deve-se impedir que se cadastre sessões onde ocorra interseção na exibição dos filmes em uma mesma sala. O administrador deve informar um período pelo 
    qual o filme continuará em exibição.

11. <b>Listar todas as sessões</b> – o sistema deve permitir visualizar todas as sessões em uma única tabela. Deve aparecer a informação se a sessão está 
    ativa ou não.

12. <b>Consultar os detalhes de uma sessão</b> – o administrador deve poder ver informações como quantidade de ingressos vendidos e valor arrecadado em uma 
    sessão de um filme. Como uma sessão se repete diariamente, essas informações devem poder ser filtradas por dia.

13. <b>Interromper sessão</b> – administrador deve ter a opção de interromper uma sessão de exibição de um filme a partir de um dado momento. Isso será útil 
    quando for identificado que não estão sendo vendidos ingressos para justificar a existência dessa sessão. Quando uma sessão é interrompida, não deve mais 
    ser possível vender ingressos para ela e o horário da sala fica disponível para ser usado por outro filme.

14. <b>Cadastrar cliente</b> – o sistema deve permitir que usuários comuns realizem um cadastro como cliente, informando, pelo menos, um e-mail e uma senha. 

15. <b>Vender ingresso</b> – um cliente devidamente autenticado deve ter a possibilidade de poder comprar ingressos para uma sessão. Ele deve informar a 
    quantidade de ingressos que deseja comprar e que lugares, entre os disponíveis, ele deseja reservar. Não deve ser possível comprar ingressos para uma 
    sessão que já começou. O sistema deve gerar um “boleto fictício” que representará o quanto o cliente deverá pagar.

16. <b>Cancelar a sessão em um dia</b> – o administrador deve pode cancelar uma sessão específica de exibição. Nesse caso, os clientes que compraram ingressos 
    para essa sessão devem ser informados por e-mail.

17. <b>Gerar relatório estratégico</b> – esse relatório, no formato PDF, deve permitir que o administrador consiga as seguintes informações: quanto foi arrecado 
    em cada sessão, quanto foi arrecado por cada filme, quantos ingressos foram vendidos e quantos ficaram vagos em cada sessão. O usuário deve poder filtrar as 
    informações do relatório por mês. 
