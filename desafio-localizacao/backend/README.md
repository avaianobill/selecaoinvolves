# Backend project

Projeto desenvolvido em java utilizando o framework SpringBoot.

# Armazenamento dos dados em banco de dados

Os arquivos fornecidos em extensão .cvs são carregados na inicialização do sistema e armazenados no banco de dados em memória H2.

# Organização interna do projeto

Foram criadas duas abstrações conforme os arquivos do CVS de domínio (Funcionario e Loja).

Dessa forma, criados dois conjuntos de controladores, serviços e repositórios.

Criado também controlador e serviço de vinculação entre funcionários e lojas.

Além do uso de DTOs para transferência de dados da camada de controladores para os serviços.





