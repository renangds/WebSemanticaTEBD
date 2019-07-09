<%@ page import="java.util.List" %>
<%@ page import="Controller.ConsultasMedicasServlet" %><%--
  Created by IntelliJ IDEA.
  User: renan
  Date: 09/07/2019
  Time: 10:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listagem Médico <%=request.getParameter("medico")%></title>
</head>
<body>
    <h1>Listagem Médico <%=request.getParameter("medico")%></h1>

    <%
        List<String> horarios = ConsultasMedicasServlet.listarHorariosMedico(request.getParameter("medico"));

        for(String horario: horarios){
    %>

    <p><%=horario%></p>

    <%
        }
    %>
</body>
</html>
