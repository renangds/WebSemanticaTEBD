import org.apache.jena.query.*;
import org.apache.jena.rdf.model.RDFNode;

public class DatabaseJenaTest {

    public static void execSelectAndPrint(String serviceURI, String query) {
        QueryExecution q = QueryExecutionFactory.sparqlService(serviceURI,
                query);
        ResultSet results = q.execSelect();

        ResultSetFormatter.out(System.out, results);

        while (results.hasNext()) {
            QuerySolution soln = results.nextSolution();
            RDFNode x = soln.get("x");
            System.out.println(x);
        }
    }


    public static void main(String...args){
        execSelectAndPrint(
                "http://localhost:3030/testes",
                "SELECT ?x ?nomes WHERE { ?x  <http://www.w3.org/2001/vcard-rdf/3.0#FN>  ?nomes }");
    }
}
