package Model;

import org.apache.jena.iri.impl.Main;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.util.FileManager;

import java.util.List;
import java.util.ArrayList;

public class Querys {
    private String serviceURI;

    public Querys(){
        FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
        this.serviceURI = "http://localhost:3030/Hea2";
    }

    public List <String> getQueryFromDatabase(String query){
        List <String> result = new ArrayList<>();

        QueryExecution q = QueryExecutionFactory.sparqlService(this.serviceURI, query);

        ResultSet results = q.execSelect();

        while(results.hasNext()){
            QuerySolution soln = results.nextSolution();
            RDFNode x = soln.get("nome");
            Literal y = soln.getLiteral("nome");
            result.add(y.getString());
        }

        q.close();

        return result;
    }

    public List<Medico> getQueryDoctors(String query){
        QueryExecution q = QueryExecutionFactory.sparqlService(this.serviceURI, query);

        List <Medico> medicos = new ArrayList<>();

        ResultSet results = q.execSelect();

        while(results.hasNext()){
            QuerySolution soln = results.nextSolution();
            Literal x = soln.getLiteral("nome");
            Literal y = soln.getLiteral("crm");
            medicos.add(new Medico(x.getString(), y.getString()));
        }

        q.close();

        return medicos;
    }

    public Medico getMedicoQuery(String query){
        QueryExecution q = QueryExecutionFactory.sparqlService(this.serviceURI, query);

        ResultSet results = q.execSelect();

        QuerySolution soln = results.nextSolution();
        Literal x = soln.getLiteral("nome");
        Literal y = soln.getLiteral("ano");
        Literal z = soln.getLiteral("valor");

        Medico medico = new Medico(x.getString(), null);
        medico.setAnoFormacao(y.getString());
        medico.setValor_consulta(z.getString());

        q.close();

        return medico;
    }

    public List<String> searchAreas(){
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                " PREFIX E: <http://consultasmedicas.io/especialidade/>" +
                " SELECT ?nome ?nome2" +
                " WHERE {" +
                "?x E:nome_especialidade ?nome.}";

        List <String> result = this.getQueryFromDatabase(query);

        return result;
    }

    public List<String> searchByAreas(String area){
        String query = "PREFIX M: <http://consultasmedicas.io/medico/> " +
                "PREFIX E: <http://consultasmedicas.io/especialidade/> " +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "SELECT ?nome " +
                "WHERE{" +
                "E:" + area +" E:id_especialidade ?id." +
                "?medico E:id_especialidade ?id." +
                "?medico M:nome_medico ?nome." +
                "?medico M:crm ?crm" +
                "}";

        List <String> result = this.getQueryFromDatabase(query);

        return result;
    }

    public List<Medico> searchByAreas2(String area){
        String query = "PREFIX M: <http://consultasmedicas.io/medico/> " +
                "PREFIX E: <http://consultasmedicas.io/especialidade/> " +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "SELECT ?nome ?crm " +
                "WHERE{" +
                "E:" + area +" E:id_especialidade ?id." +
                "?medico E:id_especialidade ?id." +
                "?medico M:nome_medico ?nome." +
                "?medico M:crm ?crm." +
                "}";

        List<Medico> lista = this.getQueryDoctors(query);

        return lista;
    }

    public List<String> searchDatesByCrm(String crm){
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "PREFIX C: <http://consultasmedicas.io/consulta/> " +
                "PREFIX P: <http://consultasmedicas.io/paciente/> " +
                "PREFIX E: <http://consultasmedicas.io/especialidade/> " +
                "PREFIX M: <http://consultasmedicas.io/medico/> " +
                "SELECT ?nome " +
                "WHERE { " +
                "?consulta P:crm_medico \"" + crm + "\". ?consulta C:data_consulta ?nome.}";

        List <String> result = this.getQueryFromDatabase(query);

        return result;
    }

    public List<String> searchDatesByName(String name){
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "PREFIX M: <http://consultasmedicas.io/medico/> " +
                "PREFIX C: <http://consultasmedicas.io/consulta/> " +
                "PREFIX P: <http://consultasmedicas.io/paciente/> " +
                "SELECT ?data " +
                "WHERE { " +
                "?medico M:nome_medico " + name + ". ?medico M:crm ?crm. ?consulta P:crm_medico ?crm. ?consulta C:data_consulta ?data.}";

        List <String> result = this.getQueryFromDatabase(query);

        return result;
    }

    public Medico getDoctorWithCrm(String crm){
        String query = "PREFIX M: <http://consultasmedicas.io/medico/> " +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "SELECT ?nome ?ano ?valor " +
                "WHERE { ?medico M:crm \"" + crm + "\". ?medico M:nome_medico ?nome. ?medico M:ano_formacao ?ano. " +
                "?medico M:valor_consulta ?valor.}";

        Medico medico = this.getMedicoQuery(query);

        return medico;
    }

    public static void main(String...args){
        Querys q = new Querys();

        Medico m = q.getDoctorWithCrm("1");

        System.out.println(m.getAno_formacao());
        System.out.println(m.getNome());
        System.out.println(m.getValor_consulta());
    }
}
