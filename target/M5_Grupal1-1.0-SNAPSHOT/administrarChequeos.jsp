
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="navbar.jsp"%>
<%@include file="footer.jsp"%>
<html>
<%String tipoSelec = (String)request.getSession().getAttribute("tipoSeleccionado");%>
<% if (usuarioConectado != null && usuarioConectado && tipoSelec.equals("Administrativo")) { %>
<head>
    <title>Administrar Chequeos</title>
</head>
<body>
<h1 class="text-center">Administrar Chequeos</h1>







<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>

<% } else { %>
<% response.sendRedirect("login.jsp"); %>
<% } %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</html>
