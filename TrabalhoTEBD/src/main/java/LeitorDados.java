import org.apache.jena.base.Sys;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeitorDados {
    private Model model;
    private List <String> lineReader;
    private String URIPrefix;

    public LeitorDados(){
        this.URIPrefix = "http://consultasmedicas.io/";
        this.lineReader = new ArrayList<>();
        this.model = ModelFactory.createDefaultModel();
    }

    /*
    public static void main(String...args) throws FileNotFoundException, IOException {
        BufferedReader dados = new BufferedReader(new FileReader("/home/renan/IdeaProjects/TrabalhoTEBD/" +
                "/src/data/especialidade.txt"));

        List <String> a;

        Model model = ModelFactory.createDefaultModel();

        while(dados.ready()){
            String linha = dados.readLine();

            a = Arrays.asList(linha.split("[|]"));

            a.forEach(System.out::println);

            Resource newResource = model.createResource("http://consultasmedicas.io/especialidade/" + a.get(1))
                    .addProperty(VCARD.FN, a.get(1))
                    .addProperty(VCARD.FN, a.get(0))
                    .addProperty(VCARD.FN, a.get(2));
        }

        File file = new File("especialidades.rdf");

        FileWriter writter = new FileWriter(file);

        model.write(writter);

        dados.close();
    }
     */

    public void especialidadeToRdf() throws IOException{
        String typeProperty = this.URIPrefix + "especialidade/";

        BufferedReader dados = new BufferedReader(new FileReader("/home/renan/IdeaProjects/TrabalhoTEBD/" +
                "/src/data/especialidade.txt"));

        Property id_especialidade = model.createProperty(typeProperty + "id_especialidade");
        Property nome_especialidade = model.createProperty(typeProperty + "nome_especialidade");
        Property descricao_especialidade = model.createProperty(typeProperty + "descricao_especialidade");

        while(dados.ready()){
            String linha = dados.readLine();

            this.lineReader = Arrays.asList(linha.split("[|]"));

            Resource newResource = model.createResource(typeProperty + lineReader.get(1))
                    .addProperty(id_especialidade, lineReader.get(0))
                    .addProperty(nome_especialidade, lineReader.get(1))
                    .addProperty(descricao_especialidade, lineReader.get(2));
        }

        File file = new File("especialidades2.rdf");

        FileWriter writer = new FileWriter(file);

        model.write(writer);

        dados.close();
    }

    public void medicoToRdf() throws IOException{
        String typeProperty = this.URIPrefix + "medico/";
        String especialidadeProperty = this.URIPrefix + "especialidade/";

        BufferedReader dados = new BufferedReader(new FileReader("/home/renan/IdeaProjects/TrabalhoTEBD/" +
                "/src/data/medico.txt"));

        Property id_especialidade = this.model.createProperty(especialidadeProperty + "id_especialidade");
        Property id_crm = this.model.createProperty(typeProperty + "crm");
        Property nome_medico = this.model.createProperty(typeProperty + "nome_medico");
        Property ano_formacao = this.model.createProperty(typeProperty + "ano_formacao");
        Property valor_consulta = this.model.createProperty(typeProperty + "valor_consulta");

        while(dados.ready()){
            String linha = dados.readLine();

            this.lineReader = Arrays.asList(linha.split("[|]"));

            Resource newResource = model.createResource(typeProperty + lineReader.get(0))
                    .addProperty(id_crm, lineReader.get(0))
                    .addProperty(nome_medico, lineReader.get(1))
                    .addProperty(id_especialidade, lineReader.get(2))
                    .addProperty(ano_formacao, lineReader.get(3))
                    .addProperty(valor_consulta, lineReader.get(4));
        }

        File file = new File("medicos.rdf");

        FileWriter writer = new FileWriter(file);

        model.write(writer);

        dados.close();
    }

    public void pacienteToRdf() throws IOException{
        String typeProperty = this.URIPrefix + "paciente/";

        BufferedReader dados = new BufferedReader(new FileReader("/home/renan/IdeaProjects/TrabalhoTEBD/" +
                "/src/data/paciente.txt"));

        Property nome_paciente = this.model.createProperty(typeProperty + "nome_paciente");
        Property id_paciente = this.model.createProperty(typeProperty + "cpf_paciente");
        Property telefone_paciente = this.model.createProperty(typeProperty + "telefone");

        while(dados.ready()){
            String linha = dados.readLine();

            this.lineReader = Arrays.asList(linha.split("[|]"));

            Resource newResource = model.createResource(typeProperty + lineReader.get(0))
                    .addProperty(id_paciente, lineReader.get(0))
                    .addProperty(nome_paciente, lineReader.get(1))
                    .addProperty(telefone_paciente, lineReader.get(2));
        }

        File file = new File("pacientes.rdf");

        FileWriter writer = new FileWriter(file);

        model.write(writer);

        dados.close();
    }

    public void consultasToRdf() throws IOException{
        String typeProperty = this.URIPrefix + "consulta/";
        String pacienteProperty = this.URIPrefix + "paciente/";
        String medicoProperty = this.URIPrefix + "medico/";

        BufferedReader dados = new BufferedReader(new FileReader("/home/renan/IdeaProjects/TrabalhoTEBD/" +
                "/src/data/consulta.txt"));

        Property data_consulta = this.model.createProperty(typeProperty + "data_consulta");
        Property crm_medico = this.model.createProperty(pacienteProperty + "crm_medico");
        Property id_paciente = this.model.createProperty(medicoProperty + "id_paciente");

        while(dados.ready()){
            String linha = dados.readLine();

            this.lineReader = Arrays.asList(linha.split("[|]"));

            Resource newResource = model.createResource(typeProperty + lineReader.get(1))
                    .addProperty(data_consulta, lineReader.get(0))
                    .addProperty(crm_medico, lineReader.get(1))
                    .addProperty(id_paciente, lineReader.get(2));
        }

        File file = new File("consultas.rdf");

        FileWriter writer = new FileWriter(file);

        model.write(writer);

        dados.close();
    }

    public static void main(String...args) throws IOException{
        LeitorDados leitor = new LeitorDados();

        leitor.consultasToRdf();
    }
}
