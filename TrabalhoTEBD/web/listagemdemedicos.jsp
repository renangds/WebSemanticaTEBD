<%@ page import="Model.Querys" %>
<%@ page import="java.util.List" %>

<%--
  Created by IntelliJ IDEA.
  User: renan
  Date: 08/07/2019
  Time: 02:30
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listagem de Médicos</title>
</head>
<body>
    <h1>Listagem de Médicos</h1>

    <%
        Querys query = new Querys();
        List <String> lista = query.searchByAreas("cardiologista");
    %>

    <h1>"<%= request.getParameter("especialidade") %>"</h1>
</body>
</html>
