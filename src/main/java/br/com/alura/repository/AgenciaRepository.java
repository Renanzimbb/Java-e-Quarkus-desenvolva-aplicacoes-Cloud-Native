package br.com.alura.repository;

import br.com.alura.domain.Agencia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


// O Panache é um ORM — como o Hibernate — usado para classes de domínio, como Agencia e Endereco, representando tabelas do banco de dados. 
// Ao salvar dados, os ORMs tratam a conversão da classe de domínio para a tabela do banco, facilitando a interação.
// Para o Quarkus injetar recursos entre classes e utilizar o CDI, anotamos como @ApplicationScoped.
// Para isso, utilizamos a anotação @ApplicationScoped, que informa ao Quarkus que a classe deve ser gerenciada e injetada em outros recursos, 
// como uma controller. Isso é feito através do CDI (Context and Dependency Injection), parte da especificação do Jakarta e do MicroProfile.


@ApplicationScoped
public class AgenciaRepository implements PanacheRepository<Agencia>{
    
}
