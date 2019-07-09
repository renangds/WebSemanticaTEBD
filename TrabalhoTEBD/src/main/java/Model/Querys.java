package Model;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.RDFNode;
import java.util.List;
import java.util.ArrayList;

public class Querys {
    private String serviceURI;

    public Querys(){
        this.serviceURI = "http://localhost:3030/Hea2";
    }

    public List <String> getQueryFromDatabase(String query){
        List <String> result = new ArrayList<>();

        QueryExecution q = QueryExecutionFactory.sparqlService(this.serviceURI, query);

        ResultSet results = q.execSelect();

        //ResultSetFormatter.out(System.out, results);

        while(results.hasNext()){
            QuerySolution soln = results.nextSolution();
            RDFNode x = soln.get("nome");
            Literal y = soln.getLiteral("nome");
            result.add(y.getString());
        }

        result.forEach(System.out::println);

        return result;
    }

    public ResultSet getQueryFromDatabase2(String query){
        List <String> result = new ArrayList<>();

        QueryExecution q = QueryExecutionFactory.sparqlService(this.serviceURI, query);

        ResultSet results = q.execSelect();

        return results;
    }

    public List<String> searchAreas(){
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                " PREFIX E: <http://consultasmedicas.io/especialidade/>" +
                " SELECT ?nome " +
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

    public ResultSet searchByAreas2(String area){
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

        ResultSet result = this.getQueryFromDatabase2(query);

        return result;
    }

    public List<String> searchDatesByCrm(String crm){
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "PREFIX C: <http://consultasmedicas.io/consulta/> " +
                "PREFIX P: <http://consultasmedicas.io/paciente/> " +
                "SELECT ?nome " +
                "WHERE { " +
                "?consulta P:crm_medico " + crm + ". ?consulta C:data_consulta ?nome.}";

        List <String> result = this.getQueryFromDatabase(query);

        return result;
    }

    public void searchByData(String doctor){

    }

    public static void main(String...args){
        Querys q = new Querys();

        q.searchDatesByCrm("22");
    }
}
