package br.com.alura.domain.http;

public class AgenciaHttp {
    String nome;
    String razaoSocial;
    SituacaoCadastral situacao;
    String cnpj;

    public String getNome() {
        return nome;
    }
    public String getRazaoSocial() {
        return razaoSocial;
    }
    public SituacaoCadastral getSituacao() {
        return situacao;
    }
    public String getCnpj() {
        return cnpj;
    } 
    
}
