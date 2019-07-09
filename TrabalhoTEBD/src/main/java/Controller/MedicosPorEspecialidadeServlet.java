package Controller;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import Model.Querys;
import org.apache.jena.query.ResultSet;

@WebServlet(name="medicosporespecialidade", urlPatterns = {"/medicosporespecialidade"})
public class MedicosPorEspecialidadeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("especialidade").equals("1")){
            request.setAttribute("nomeespecialidade", "dermatologista");
            request.getRequestDispatcher("/listagemdemedicos.jsp").forward(request, response);
        } else if(request.getParameter("especialidade").equals("2")){
            request.setAttribute("nomeespecialidade", "cardiologista");
            request.getRequestDispatcher("/listagemdemedicos.jsp").forward(request, response);
        } else{
            request.setAttribute("nomeespecialidade", "hepatologista");
            request.getRequestDispatcher("/listagemdemedicos.jsp").forward(request, response);
        }
    }
}
