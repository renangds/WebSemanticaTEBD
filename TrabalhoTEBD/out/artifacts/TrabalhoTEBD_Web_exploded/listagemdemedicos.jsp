
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
<%@ page import="Model.Medico" %>

<html>
<head>
    <title>Listagem de Médicos</title>
</head>
<body>
    <h1>Listagem de Médicos</h1>

        <form action="consultasmedicas">
            <select name="medico">
                <%
                    List<Medico> listaMedico = MedicosPorEspecialidadeServlet.listagemDeEspecialidades2(request.getParameter("especialidade"));

                    for(int i=0; i<listaMedico.size()-1; i++){
                %>

                <option value=<%= listaMedico.get(i).getCRM() %>> <%= listaMedico.get(i).getNome() %></option>

                <%
                    }
                %>
            </select>
        <input type="submit" name="Listar"/>
    </form>
</body>
</html>
