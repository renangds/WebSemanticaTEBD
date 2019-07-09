package Controller;

import Model.Querys;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/consultasmedicas")
public class ConsultasMedicasServlet extends HttpServlet {

    public static List<String> listarHorariosMedico(String crm){
        List <String> list;
        Querys query = new Querys();

        list = query.searchDatesByCrm(crm);

        return list;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.getRequestDispatcher("/listagemhorarios.jsp").forward(request, response);
    }
}
