package Model;

import org.apache.jena.base.Sys;

public class Medico {
    private String CRM;
    private String nome;
    private String codigo_especialidade;
    private String ano_formacao;
    private float valor_consulta;

    public Medico(String nome, String crm){
        this.CRM = crm;
        this.nome = nome;
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
