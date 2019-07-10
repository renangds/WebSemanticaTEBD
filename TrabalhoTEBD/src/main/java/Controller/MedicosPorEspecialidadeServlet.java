package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import Model.Medico;
import Model.Querys;

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


    public static List<Medico> listagemDeEspecialidades2(String especialidade){
        Querys querys = new Querys();

        List<Medico> listaMedicos = querys.searchByAreas2(especialidade);

        return listaMedicos;
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("nomeespecialidade", request.getParameter("especialidade"));
        request.getRequestDispatcher("/listagemdemedicos.jsp").forward(request, response);
    }
}
