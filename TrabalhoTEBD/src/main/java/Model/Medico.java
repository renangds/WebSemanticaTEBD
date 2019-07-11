package Model;

import org.apache.jena.base.Sys;

public class Medico {
    private String CRM;
    private String nome;
    private String codigo_especialidade;
    private String ano_formacao;
    private String valor_consulta;

    public Medico(String nome, String crm){
        this.CRM = crm;
        this.nome = nome;
    }

    public void setAnoFormacao(String ano){
        this.ano_formacao = ano;
    }

    public void setValor_consulta(String valor){
        this.valor_consulta = valor;
    }

    public String getAno_formacao(){
        return this.ano_formacao;
    }

    public String getValor_consulta(){
        return this.valor_consulta;
    }

    public String toString(){
        return this.CRM + " " + this.nome;
    }

    public String getCRM(){
        return this.CRM;
    }

    public String getNome(){
        return this.nome;
    }
}
