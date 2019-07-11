<%@ page import="java.util.List" %>
<%@ page import="Controller.MedicosPorEspecialidadeServlet" %><%--
  Created by IntelliJ IDEA.
  User: renan
  Date: 23/06/2019
  Time: 02:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Sistema de Consulta Médica</h1>

    <form action="medicosporespecialidade">
        <select name="especialidade">
            <%
                List <String> list = MedicosPorEspecialidadeServlet.listagemDeEspecialidades();

                for(String nome : list){
            %>

            <option value=<%=nome%>><%=nome%></option>

            <%
                }
            %>
            <input type="submit" name="Consultar"/>
        </select>
    </form>
</body>
</html>
