package br.com.alura.service;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import br.com.alura.domain.Agencia;
import br.com.alura.domain.http.AgenciaHttp;
import br.com.alura.domain.http.SituacaoCadastral;
import br.com.alura.exceptions.AgenciaNaoAtivaOuNaoEcontradaException;
import br.com.alura.repository.AgenciaRepository;
import br.com.alura.service.http.SituacaoCadastralHttpService;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

// Para isso, utilizamos a anotação @ApplicationScoped, que informa ao Quarkus que a classe deve ser gerenciada e injetada em outros recursos, 
// como uma controller. Isso é feito através do CDI (Context and Dependency Injection), parte da especificação do Jakarta e do MicroProfile.

@ApplicationScoped
public class AgenciaService {

    @RestClient
    private SituacaoCadastralHttpService SituacaoCadastralHttpService;

    private final AgenciaRepository agenciaRepository;

    private final MeterRegistry meterRegistry;

    AgenciaService(AgenciaRepository agenciaRepository, MeterRegistry meterRegistry) {
        this.agenciaRepository = agenciaRepository;
        this.meterRegistry = meterRegistry;
    }

    public void cadastrar(Agencia agencia){
        AgenciaHttp agenciaHttp = SituacaoCadastralHttpService.buscarPorCnpj(agencia.getCnpj());

        if(agenciaHttp != null && agenciaHttp.getSituacao().equals(SituacaoCadastral.ATIVO)){
            
            Log.info("A agência com o CNPJ " + agencia.getCnpj() + " foi cadastrada");
            meterRegistry.counter("agencia_adiconada_counter").increment();
            agenciaRepository.persist(agencia);
        } else{
            Log.info("A agência com o CNPJ " + agencia.getCnpj() + " não foi cadastrada");
            meterRegistry.counter("agencia__nao_adiconada_counter").increment();
            throw new AgenciaNaoAtivaOuNaoEcontradaException();
        }
       
    }

    public Agencia buscarPorId(Long id){
        return agenciaRepository.findById(id);
    }

    public void deletar(Long id){
        agenciaRepository.deleteById(id);
    }

    public void alterar(Agencia agencia){
        agenciaRepository.update("nome = ?1, razaoSocial = ?2, cnpj = ?3 where id = ?4", agencia.getNome(), agencia.getRazaoSocial(), agencia.getCnpj(), agencia.getId());
    }
    
}
