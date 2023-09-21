<%--
  Created by IntelliJ IDEA.
  User: Ganon
  Date: 25-07-2023
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navbar.jsp"%>
<%@include file="footer.jsp"%>
<html>
<%String tipoSelec = (String)request.getSession().getAttribute("tipoSeleccionado");%>
<% if (usuarioConectado != null && usuarioConectado && tipoSelec.equals("Cliente")) { %>
<head>
    <title>Administrar Asistentes</title>
</head>
<body>
<h1 class="text-center">Administrar Asistentes</h1>

<div class="text-center">
  <button type="button" class="btn btn-outline-primary btn-dark mb-3">Crear Asistente</button>
  <button type="button" class="btn btn-outline-danger btn-dark mb-3">Eliminar Asistente</button>
  <button type="button" class="btn btn-outline-success btn-dark mb-3">Listar Asistentes</button>
</div>

</body>
<% } else { %>
<% response.sendRedirect("login.jsp"); %>
<% } %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</html>
