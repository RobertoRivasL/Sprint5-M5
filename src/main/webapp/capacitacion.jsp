<%@ page import="models.Capacitacion" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@include file="navbar.jsp" %>
<%@include file="footer.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<body>
<%String tipoSelec = (String) request.getSession().getAttribute("tipoSeleccionado");%>
<% if (usuarioConectado != null && usuarioConectado && tipoSelec.equals("Cliente")) { %>
<h1 class="text-center">Capacitación</h1>
<div class="b-example-divider mt-5"></div>

<!-- Botón para mostrar el modal de creación de capacitación -->
<button id="abrirModalCreacion" class="btn btn-primary mb-3">Crear Capacitación</button>

<!-- Modal de creación de capacitación -->
<div class="modal fade" id="modalCreacionCapacitacion" tabindex="-1"
     aria-labelledby="modalCreacionCapacitacionLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <!--<h5 class="modal-title" id="modalCreacionCapacitacionLabel">Crear Capacitación</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>-->
                <h5 class="modal-title" id="modalCrearCapacitacionLabel">Crear capacitación</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="formulario" action="svCapacitacion" method="POST">
                    <div class="mb-3">
                        <label for="rut_cliente" class="form-label">Rut Cliente</label>
                        <input type="number" class="form-control" id="rut_cliente" name="rutCliente" minlength="8"
                               maxlength="9" required>
                        <label class="d-none text-danger" id="alertaRut_cliente">Falta agregar el rut del
                            cliente</label>
                    </div>
                    <div class="mb-3">
                        <label for="dia" class="form-label">Día</label>
                        <select class="form-select" id="dia" name="dia" required>
                            <!-- Agrega tus opciones aquí -->
                            <option value="lunes">Lunes</option>
                            <option value="martes">Martes</option>
                            <option value="miercoles">Miércoles</option>
                            <option value="jueves">Jueves</option>
                            <option value="viernes">Viernes</option>
                            <option value="sabado">Sábado</option>
                            <option value="domingo">Domingo</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="hora" class="form-label">Hora</label>
                        <select class="form-select" id="hora" name="hora" required>
                            <!-- Agrega las horas de 10:00 AM a 23:00 PM -->
                            <%
                                for (int j = 10; j <= 24; j++) {
                                    String hora = String.format("%02d:00", j);
                            %>
                            <option value="<%= hora %>"><%= hora %>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="lugar" class="form-label">Lugar</label>
                        <input type="text" class="form-control" id="lugar" name="lugar" minlength="10"
                               maxlength="50" required>
                        <label class="d-none text-danger" id="alertaLugar">Falta agregar el lugar</label>
                    </div>
                    <div class="mb-3">
                        <label for="duracion" class="form-label">Duración</label>
                        <input type="text" class="form-control" id="duracion" name="duracion" maxlength="70"
                               required>
                        <label class="d-none text-danger" id="alertaDuracion">Falta agregar la Duración</label>
                    </div>
                    <div class="mb-3">
                        <label for="cantidadAsistentes" class="form-label">Cantidad de Asistentes</label>
                        <input type="number" class="form-control" id="cantidadAsistentes" name="cantidadAsistentes"
                               maxlength="1000" required>
                        <label class="d-none text-danger" id="alertaCantidadAsistentes">Falta agregar la cantidad de
                            Asistentes</label>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                <button id="botonCrear" type="submit" form="formulario" class="btn btn-primary">Crear</button>
            </div>
        </div>
    </div>
</div>

<!-- Mensaje de éxito -->
<div class="center" style="margin-top:10px; margin-bottom: 20px;">
    <div class="alert alert-success d-none" id="capCreada">Su capacitación ha sido creada</div>
</div>
</div>

<!-- Script para mostrar el botón de Crear Capacitación al hacer clic en el botón -->
<script>
    const abrirModalCreacion = document.getElementById("abrirModalCreacion");
    const capCreada = document.getElementById("capCreada");

    abrirModalCreacion.addEventListener("click", function () {
        // Abre el modal de creación de capacitación
        const modal = new bootstrap.Modal(document.getElementById("modalCreacionCapacitacion"));
        modal.show();
        // Limpia los campos y mensajes de error cuando se abre el modal
        document.getElementById("formulario").reset();
        capCreada.classList.add("d-none");
        document.getElementById("alertaRut_cliente").classList.add("d-none");
        document.getElementById("alertaLugar").classList.add("d-none");
        document.getElementById("alertaDuracion").classList.add("d-none");
        document.getElementById("alertaCantidadAsistentes").classList.add("d-none");
    });
</script>
<% } else { %>
<% response.sendRedirect("login.jsp"); %>
<% } %>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>
</body>
</html>