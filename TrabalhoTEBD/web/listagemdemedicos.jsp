
<%--
  Created by IntelliJ IDEA.
  User: renan
  Date: 08/07/2019
  Time: 02:30
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Controller.MedicosPorEspecialidadeServlet" %>
<%@ page import="java.util.List" %>

<html>
<head>
    <title>Listagem de Médicos</title>
</head>
<body>
    <h1>Listagem de Médicos</h1>

        <form action="consultasmedicas">
            <select name="medico">
                <%
                    List <String> lista = MedicosPorEspecialidadeServlet.listarMedicos(request.getParameter("especialidade"));

                    //List <String> nomes = MedicosPorEspecialidadeServlet.listagemDeEspecialidades2(request.getParameter("especialidade")).getNames();
                    //List <String> crms = MedicosPorEspecialidadeServlet.listagemDeEspecialidades2(request.getParameter("especialidade")).getCrms();

                    for(String nomeMedico : lista){
                    //for(int i=0; i<nomes.size()-1; i++){
                %>

                <option value=<%= nomeMedico %>><%= nomeMedico %></option>

                <%
                    }
                %>
            </select>
        <input type="submit" name="Listar"/>
    </form>
</body>
</html>
