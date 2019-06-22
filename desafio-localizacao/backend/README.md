# Backend project

Projeto desenvolvido em java utilizando o framework SpringBoot.

# Armazenamento dos dados em banco de dados

Os arquivos fornecidos em extensão .cvs são carregados na inicialização do sistema e armazenados no banco de dados em memória H2.

# Organização interna do projeto

Foram criadas duas abstrações conforme os arquivos do CVS de domínio (Funcionario e Loja).
Dessa forma, criados dois conjuntos de controladores, serviços e repositórios, além de um controlador e serviço de vinculação entre funcionários e lojas.
Uso de DTOs para transferência de dados da camada de controle para os serviços.





