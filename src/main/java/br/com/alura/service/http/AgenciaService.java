package br.com.alura.service.http;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.alura.domain.Agencia;
import br.com.alura.domain.http.AgenciaHttp;
import br.com.alura.domain.http.SituacaoCadastral;
import br.com.alura.exceptions.AgenciaNaoAtivaOuNaoEcontradaException;
import jakarta.enterprise.context.ApplicationScoped;

// Para isso, utilizamos a anotação @ApplicationScoped, que informa ao Quarkus que a classe deve ser gerenciada e injetada em outros recursos, 
// como uma controller. Isso é feito através do CDI (Context and Dependency Injection), parte da especificação do Jakarta e do MicroProfile.

@ApplicationScoped
public class AgenciaService {

    @RestClient
    private SituacaoCadastralHttpService SituacaoCadastralHttpService;

    private List<Agencia> agencias = new ArrayList<>();

    public void cadastrar(Agencia agencia){
        AgenciaHttp agenciaHttp = SituacaoCadastralHttpService.buscarPorCnpj(agencia.getCnpj());

        if(agenciaHttp != null && agenciaHttp.getSituacao().equals(SituacaoCadastral.ATIVO)){
            agencias.add(agencia);
        } else{
            throw new AgenciaNaoAtivaOuNaoEcontradaException();
        }
       
    }

    public Agencia buscarPorId(Integer id){
        return agencias.stream()
                        .filter(agencia -> agencia.getId()
                        .equals(id))
                        .toList()
                        .getFirst();
    }

    public void deletar(Integer id){
        agencias.removeIf((agencia -> agencia.getId().equals(id)));
    }

    public void alterar(Agencia agencia){
        deletar(agencia.getId());
        cadastrar(agencia);
    }
    
}
