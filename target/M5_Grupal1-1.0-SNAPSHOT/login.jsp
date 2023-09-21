<%@include file="footer.jsp" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Iniciar sesión</title>
    <!-- Incluye los estilos de Bootstrap y tu propio archivo CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/style.css">
    <style>
        /* Agrega estilos personalizados aquí si es necesario */
    </style>
</head>
<body>
<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-lg-4 col-md-6 col-sm-8">
            <form class="login-form" action="svLogin" method="POST">
                <h2 class="text-center mb-4">Iniciar sesión</h2>
                <div class="mb-3">
                    <label for="Usuario" class="form-label">Rut de Usuario</label>
                    <input name="rutUsuario" type="text" id="Usuario" class="form-control"
                           placeholder="Ingresa rut de usuario" required>
                </div>
                <div class="mb-3">
                    <label for="passwordx" class="form-label">Contraseña</label>
                    <input name="password" type="password" id="passwordx" class="form-control"
                           placeholder="Ingresa tu contraseña" required>
                </div>
                <div class="d-flex justify-content-between align-items-center">
                    <button type="submit" class="btn btn-primary">Ingresar</button>
                    <a href="register.jsp" class="btn btn-secondary">Registrar</a>
                </div>

                <%
                    Boolean usuarioConectado = (Boolean) session.getAttribute("usuarioConectado");
                    if (usuarioConectado != null && !usuarioConectado) { %>
                <div class="alert alert-danger mt-3" role="alert">
                    Contraseña incorrecta. Por favor, inténtalo de nuevo.
                </div>
                <% } %>

            </form>
        </div>
    </div>
</div>


<script>
    <% Boolean error = (Boolean)request.getSession().getAttribute("errorLogin");%>

    <% if(error != null && error){ %>
    var alertElement = document.getElementById('alertPass');
    alertElement.style.display = 'block';
    var tiempoOcultar = 3000; // 3 segundos

    // Función para ocultar el elemento después del tiempo especificado
    function ocultarElemento() {
        alertElement.style.display = 'none';
    }
    // Configurar el temporizador con setTimeout
    setTimeout(ocultarElemento, tiempoOcultar);
    <%}%>
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
</body>
</html>