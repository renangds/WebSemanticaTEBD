import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.VCARD;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestesJena {

    public static void main(String...args) throws IOException {
        String personURI = "http://somewhere/johnsmith";
        String fullname = "John Smith";
        String givenName = "John";
        String familyName = "Smith";

        String personURI2 = "http://somewhere/renansilva";
        String fullname2 = "Renan Silva";
        String givenname2 = "Renan";
        String familyname2 = "Silva";

        Model model = ModelFactory.createDefaultModel();

        Resource johnSmith = model.createResource(personURI)
                .addProperty(VCARD.FN, fullname)
                .addProperty(VCARD.N,
                        model.createResource()
                .addProperty(VCARD.Given, givenName)
                .addProperty(VCARD.Family, familyName));

        Resource renanSilva = model.createResource(personURI2)
                .addProperty(VCARD.FN, fullname2)
                .addProperty(VCARD.N,
                        model.createResource()
                .addProperty(VCARD.Given, givenname2)
                .addProperty(VCARD.Family, familyname2));

        model.write(System.out);

        File file = new File("teste.rdf");

        FileWriter writer = new FileWriter(file);

        model.write(writer);

        //johnSmith.addProperty(VCARD.FN, fullname);

        //System.out.println(johnSmith);

        // list the statements in the graph
        StmtIterator iter = model.listStatements();

        // print out the predicate, subject and object of each statement
        while (iter.hasNext()) {
            Statement stmt      = iter.nextStatement();         // get next statement
            Resource  subject   = stmt.getSubject();   // get the subject
            Property  predicate = stmt.getPredicate(); // get the predicate
            RDFNode   object    = stmt.getObject();    // get the object

            System.out.print(subject.toString());
            System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }
            System.out.println(" .");
        }
    }

}
