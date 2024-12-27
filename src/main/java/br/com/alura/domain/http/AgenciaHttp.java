package br.com.alura.domain.http;

public class AgenciaHttp {
    String nome;
    String razaoSocial;
    SituacaoCadastral situacao;
    String cnpj;

    public AgenciaHttp(String nome, String razaoSocial, String cnpj, String situacaoCadastral) {
        this.nome = nome;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.situacao = SituacaoCadastral.valueOf(situacaoCadastral);
    }

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
