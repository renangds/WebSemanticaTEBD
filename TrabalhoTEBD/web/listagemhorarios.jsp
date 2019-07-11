<%@ page import="java.util.List" %>
<%@ page import="Controller.ConsultasMedicasServlet" %>
<%@ page import="Model.Medico" %>
<%@ page import="java.util.ArrayList" %><%--
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
    <%
        String crmMedico = request.getParameter("medico");
        Medico medico = ConsultasMedicasServlet.getDoctorData(crmMedico);
    %>

    <h1>Listagem Médico: <%=medico.getNome()%> | Valor Consulta: R$<%=medico.getValor_consulta()%>,00 | Ano Formação: <%= medico.getAno_formacao()%> </h1>

    <%

        List<String> horarios = ConsultasMedicasServlet.listarHorariosMedico(request.getParameter("medico"));
        List<String> dias = new ArrayList<>();
        List<String> horas = new ArrayList<>();
        List<Integer> horasInteger = new ArrayList<>();

        for(String hora : horarios){
            dias.add(hora.split(" ")[0]);
        }

        for(String hora: horarios){
            horas.add(hora.split(" ")[1]);
        }

        for(String h : horas){
            horasInteger.add(Integer.parseInt(h.split(":")[0]));
        }

        for(String horario: horarios){
    %>

    <p><%=horario%></p>

    <%
        }
    %>

    <table style="width: 70%; border: solid 0.5px;">
        <%
            for(int i=0; i<dias.size(); i++){
        %>
        <tr>
            <td style="width: 50px;">
                <%=dias.get(i)%>
                <%
                    for(int j=0; j<24; j++){
                        for(int k=0; k<horasInteger.size(); k++){
                            if(j == horasInteger.get(k)){
                %>
                    <%=horas.get(0)%>:00
                <%
                            }
                        }
                    }
                %>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
