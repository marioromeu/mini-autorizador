#Solução Proposta

Arquitetura baseada em Micro Serviços, composto de 3 serviços :

- Event Processor : 

    Micro Serviço responsável pelo processamento das requisições permitindo escalabilidade horizontal.

- Service Discover :
 
    Micro Serviço responsável por realizar a descoberta das instâncias de serviços online e servir de health check centralizado.

- Transaction Processor :
 
    Micro Serviço responsável pela lógica de processamento das transações e persistência.