package Controller;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import Model.DataDoctor;
import Model.Querys;
import org.apache.jena.query.ResultSet;

@WebServlet(name="medicosporespecialidade", urlPatterns = {"/medicosporespecialidade"})
public class MedicosPorEspecialidadeServlet extends HttpServlet {

    public static List <String> listarMedicos(String especialidade){
        Querys querys = new Querys();

        List <String> lista = querys.searchByAreas(especialidade);

        return lista;
    }

    public static List <String> listagemDeEspecialidades(){
        Querys querys = new Querys();

        List <String> lista = querys.searchAreas();

        return lista;
    }

    public static DataDoctor listagemDeEspecialidades2(String especialidade){
        Querys querys = new Querys();

        DataDoctor s = querys.searchByAreas2(especialidade);

        return s;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("especialidade").equals("dermatologista")){
            request.setAttribute("nomeespecialidade", "dermatologista");
            request.getRequestDispatcher("/listagemdemedicos.jsp").forward(request, response);
        } else if(request.getParameter("especialidade").equals("cardiologista")){
            request.setAttribute("nomeespecialidade", "cardiologista");
            request.getRequestDispatcher("/listagemdemedicos.jsp").forward(request, response);
        } else{
            request.setAttribute("nomeespecialidade", "hepatologista");
            request.getRequestDispatcher("/listagemdemedicos.jsp").forward(request, response);
        }
    }
}
