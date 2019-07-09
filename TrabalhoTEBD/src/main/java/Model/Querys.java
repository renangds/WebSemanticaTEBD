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


        return result;
    }

    public DataDoctor getQueryDoctors(String query){
        QueryExecution q = QueryExecutionFactory.sparqlService(this.serviceURI, query);

        DataDoctor data = new DataDoctor();

        ResultSet results = q.execSelect();

        while(results.hasNext()){
            QuerySolution soln = results.nextSolution();
            Literal x = soln.getLiteral("nome");
            Literal y = soln.getLiteral("crm");
            data.setCrms(y.getString());
            data.setNames(x.getString());
        }

        data.getCrms().forEach(System.out::println);

        return data;
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

    public DataDoctor searchByAreas2(String area){
        String query = "PREFIX M: <http://consultasmedicas.io/medico/> " +
                "PREFIX E: <http://consultasmedicas.io/especialidade/> " +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "SELECT ?nome ?crm" +
                "WHERE{" +
                "E:" + area +" E:id_especialidade ?id." +
                "?medico E:id_especialidade ?id." +
                "?medico M:nome_medico ?nome." +
                "?medico M:crm ?crm" +
                "}";

        DataDoctor d = this.getQueryDoctors(query);

        return d;
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

    public List<String> searchDatesByName(String name){
        String query = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> " +
                "PREFIX M: <http://consultasmedicas.io/medico/> " +
                "PREFIX C: <http://consultasmedicas.io/consulta/> " +
                "PREFIX P: <http://consultasmedicas.io/paciente/> " +
                "SELECT ?data " +
                "WHERE { " +
                "?medico M:nome_medico \"" + name + "\". ?medico M:crm ?crm. ?consulta P:crm_medico ?crm. ?consulta C:data_consulta ?data.}";

        List <String> result = this.getQueryFromDatabase(query);

        return result;
    }

    public void searchByData(String doctor){

    }

    public static void main(String...args){
        Querys q = new Querys();

        //q.searchDatesByCrm("22");

        //String p = "Paulo da Mata";

        //List<String> lista = q.searchDatesByName(p);

        DataDoctor d = q.searchByAreas2("cardiologista");

        //d.getNames().forEach(System.out::println);
        /*
        while(results.hasNext()){
            QuerySolution soln = results.nextSolution();
            RDFNode x = soln.get("nome");
            Literal y = soln.getLiteral("nome");
            System.out.println(y.getString());
        }
         */
    }
}
