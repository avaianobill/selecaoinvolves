# Backend project

Projeto desenvolvido em java utilizando o framework SpringBoot.

# Armazenamento dos dados em banco de dados

Os arquivos fornecidos em extensão .cvs são carregados na inicialização do sistema e armazenados no banco de dados em memória H2.

# Organização interna do projeto

Foram criadas duas abstrações conforme os arquivos do CVS de domínio (Funcionario e Loja).

Dessa forma, criados dois conjuntos de controladores, serviços e repositórios.

Criado também controlador e serviço de vinculação entre funcionários e lojas.

Além do uso de DTOs para transferência de dados da camada de controladores para os serviços.

# Execução da aplicação backend

Para executar o backend, basta seguir os passos:

1 - No diretório raiz da aplicação, executar o comando 'mvn clean package'. Esse comando irá gerar o pacote 'desafio-localizacao-1.0.0.jar' na pasta target do projeto.

2 - Para executar o pacote jar, pasta executar o comando 'java -jar desafio-localizacao-1.0.0.jar'.





