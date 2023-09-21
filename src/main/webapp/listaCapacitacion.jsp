<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="models.Capacitacion" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.CapacitacionDAOimpl" %>
<%@include file="navbar.jsp" %>
<%@include file="footer.jsp" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Capacitaciones</title>
    <link rel="stylesheet" href="./css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
</head>
<html>
<body>
<style>
    body {
        background-color: grey !important; /* Usa !important para darle mayor prioridad */
    }

</style>
<% String tipoSelec = (String) request.getSession().getAttribute("tipoSeleccionado");
    System.out.println(tipoSelec);
%>
<% if (usuarioConectado != null && usuarioConectado && tipoSelec.equals("Cliente")) { %>
<div class="container">
    <hr class="hr" style="height: 20px"/>
    <div class="row">
        <div class="col">
            <div class="table-container">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Identificador</th>
                        <th scope="col">Rut Cliente</th>
                        <th scope="col">Día</th>
                        <th scope="col">Hora</th>
                        <th scope="col">Lugar</th>
                        <th scope="col">Duración</th>
                        <th scope="col">Cantidad Asistentes</th>
                        <th scope="col">Creación</th>
                        <th scope="col">Acciones</th>
                    </tr>
                    </thead>


                    <tbody>
                    <%
                        // Obtén el número de página actual de los parámetros de la URL
                        String pageParam = request.getParameter("page");
                        int currentPage = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
                        int itemsPerPage = 6; // Cantidad de capacitaciones por página

                        // Obtén todas las capacitaciones
                        CapacitacionDAOimpl ICapacitacionDAO = new CapacitacionDAOimpl();
                        List<Capacitacion> capacitaciones = ICapacitacionDAO.listarCapacitacion();


                        // Calcula el índice de inicio y fin para las capacitaciones de la página actual
                        int startIndex = (currentPage - 1) * itemsPerPage;
                        int endIndex = Math.min(startIndex + itemsPerPage, capacitaciones.size());

                        // Calcula el número total de páginas
                        int totalPages = (int) Math.ceil((double) capacitaciones.size() / itemsPerPage);

                        for (int i = startIndex; i < endIndex; i++) {
                            Capacitacion capacitacion = capacitaciones.get(i);
                    %>
                    <tr>
                        <td><%= capacitacion.getIdentificador() %>
                        </td>
                        <td><%= capacitacion.getRutCliente() %>
                        </td>
                        <td><%= capacitacion.getDia() %>
                        </td>
                        <td><%= capacitacion.getHora() %>
                        </td>
                        <td><%= capacitacion.getLugar() %>
                        </td>
                        <td><%= capacitacion.getDuracion() %>
                        </td>
                        <td><%= capacitacion.getCantidadAsistentes() %>
                        </td>
                        <td><%= capacitacion.getCreated_at() %>
                        </td>
                        <td>
                            <form method="post" action="svEliminarCap">
                                <input type="hidden" name="identificador"
                                       value="<%= capacitacion.getIdentificador() %>">
                                <button class="btn btn-sm btn-danger" type="button"
                                        onclick="confirmarEliminacion('<%= capacitacion.getIdentificador() %>')">
                                    Eliminar
                                </button>
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Agrega los botones de "Anterior" y "Siguiente" con estilo de Bootstrap -->
    <div class="pagination">
        <%
            if (currentPage > 1) {
        %>
        <a href="listaCapacitacion.jsp?page=<%= currentPage - 1 %>" class="btn btn-primary">Anterior</a>
        <%
            }
        %>

        <%
            if (currentPage < totalPages) {
        %>
        <a href="listaCapacitacion.jsp?page=<%= currentPage + 1 %>" class="btn btn-primary">Siguiente</a>
        <%
            }
        %>
    </div>

</div>
<% } else { %>
<% response.sendRedirect("login.jsp"); %>
<% } %>

<script>
    /*Funcion confirmar la eliminacion de una capacitacion*/
    function confirmarEliminacion(identificador) {
        if (confirm("¿Estás seguro de que deseas eliminar esta capacitación?")) {
            document.querySelector('form[action="svEliminarCap"] input[name="identificador"]').value = identificador;
            document.querySelector('form[action="svEliminarCap"]').submit();
        }
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
</body>

</html>
