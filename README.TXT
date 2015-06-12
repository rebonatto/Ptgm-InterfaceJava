========================
Descrição do Protegemed
========================

Nesse arquivo encontram-se uma pasta e quatro arquivos.
- Arquivo "protegemed.ini": ajusta as confiurações de acesso ao Banco de Dados;
- Arquivo "ExecutarProtegemedLinux.sh": executa o cliente do projeto protegemed no SO Linux;
- Arquivo "ExecutarProtegemedWindows.bat": executa o cliente do projeto protegemed no SO Windows;
Arquivo "MonProtegemed.jar": arquivo executável a partir do java para acesso as informações; 
- Pasta "lib": contém bibliotecas para execução do "MonProtegemed.jar";

===============================
Execução do Cliente Protegemed
===============================

Para a execução do programa, os requisitos de hardware e software devem ser cumpridos (ver seção de requisitos).
Após, deve-se escolher o executor a iniciar, dependendo do Sistema Operacional (SO) utilizado:
Para Windows:
    Executar ProtegemedWindows.bat
Para Linux:
    Executar ProtegemedLinux.sh
    
Observações:
- Pode-se criar atalhos para a execução dos arquivos "ProtegemedWindows.bat" e "ProtegemedLinux.sh";
- Os arquivos "ProtegemedWindows.bat" e "ProtegemedLinux.sh" são descompactados com permissão de execução. Caso a permissão não esteja correta, o usuário deve ajustar.
- Não é recomendada a execução direta por meio da plataforma java do arquivo "MonProtegemed.jar".

========================
Requisitos de Hardware
========================

O sistema pode ser executado em máquinas com processador similar a um Intel Pentium Dual Core de 1GHz, com pelo menos 2GB de memória RAM. O espaço em disco deve ser compatível com os requisitos de software e quantidade de ondas manipuladas.
Recomenda-se o uso de processador similar ou superior a Intel I3, com 8Gb de memória RAM e pelo menos 2GB de espaço em HD.


========================
Requisitos de Software
========================

Os requisitos de software podem ser dividos em duas categorias: uma para máquina servidora do banco de dados e outra para máquina cliente. Não há problema em usar a mesma máquina para ser o servidor do BD e também cliente:
- Servidor:
  - Banco de dados Mysql server, versão 5.0 ou superior;
  - Servidor Web apache 2.0 (ou superior), com módulo de PHP 5 (ou superior) habilitado;
- Cliente:
  - Java Virtual Machine (JVM) instalada. Recomenda-se usar java versão 7 (todas as distribuições).



