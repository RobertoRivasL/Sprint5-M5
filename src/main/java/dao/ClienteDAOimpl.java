package dao;

import conexion.Conexion;
import idao.IClienteDAO;
import models.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOimpl implements IClienteDAO {
    @Override
    public boolean registrar(Cliente cliente) {
        boolean registrar = false;
        Statement stm = null;
        Connection con = null;

        String sql = "INSERT INTO clientes (username,password,nombre,fechaNacimiento,tipo,rutCliente,nombres,apellidos,telefono" +
                ",afp,salud,direccion,comuna,edad) values('" +
                cliente.getUsername() + "','" + cliente.getPassword() + "','" + cliente.getNombre() + "','" + cliente.getFechaNacimiento() + "'" +
                ",'" + cliente.getTipo() + "','" + cliente.getRut() + "','" + cliente.getNombres() + "','" + cliente.getApellidos() + "'" +
                ",'" + cliente.getTelefono() + "','" + cliente.getAfp() + "','" + cliente.getSistemaSalud() + "','" + cliente.getDireccion() + "'" +
                ",'" + cliente.getComuna() + "','" + cliente.getEdad() + "')";

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: clase ClienteDAOimpl en el metodo registrar");
            e.printStackTrace();
        }
        return registrar;
    }

    @Override
    public boolean eliminar(Cliente cliente) {
        boolean eliminar = false;
        Statement stm = null;
        Connection con = null;

        String sql = "DELETE from clientes where id = '" + cliente.getId() + "'";

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            stm.execute(sql);
            eliminar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: clase ClienteDAOimpl en el metodo eliminar");
            e.printStackTrace();
        }
        return eliminar;
    }

    @Override
    public boolean modificar(Cliente cliente) {
        boolean modificar = false;
        Connection con = null;
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE clientes SET " +
                "username = ?, " +
                "password = ?, " +
                "nombre = ?, " +
                "fechaNacimiento = ?, " +
                "tipo = ?, " +
                "rutCliente = ?, " +
                "nombres = ?, " +
                "apellidos = ?, " +
                "telefono = ?, " +
                "afp = ?, " +
                "salud = ?, " +
                "direccion = ?, " +
                "comuna = ?, " +
                "edad = ? " +
                "WHERE id = ?";

        try {
            con = Conexion.conectar();
            preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, cliente.getUsername());
            preparedStatement.setString(2, cliente.getPassword());
            preparedStatement.setString(3, cliente.getNombre());
            preparedStatement.setString(4, cliente.getFechaNacimiento());
            preparedStatement.setString(5, cliente.getTipo());
            preparedStatement.setString(6, cliente.getRut());
            preparedStatement.setString(7, cliente.getNombres());
            preparedStatement.setString(8, cliente.getApellidos());
            preparedStatement.setInt(9, cliente.getTelefono());
            preparedStatement.setString(10, cliente.getAfp());
            preparedStatement.setString(11, cliente.getSistemaSalud());
            preparedStatement.setString(12, cliente.getDireccion());
            preparedStatement.setString(13, cliente.getComuna());
            preparedStatement.setInt(14, cliente.getEdad());
            preparedStatement.setInt(15, cliente.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                modificar = true;
                System.out.println("Actualizacion exitosa");
            } else {
                System.out.println("Ninguna fila se ha Actualizado.");

            }

        } catch (SQLException e) {
            System.out.println("Error: clase ClienteDAOimpl en el metodo modificar");
            e.printStackTrace();
        } finally {
            // Close resources in the finally block to ensure they are always closed.
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return modificar;
    }


    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;

        String sql = "SELECT * from clientes ORDER BY id";

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);


            while (rs.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(rs.getInt(1));
                cliente.setUsername(rs.getString(2));
                cliente.setPassword(rs.getString(3));
                cliente.setNombre(rs.getString(4));
                cliente.setFechaNacimiento(rs.getString(5));
                cliente.setTipo(rs.getString(6));
                cliente.setRut(rs.getString(7));
                cliente.setNombres(rs.getString(8));
                cliente.setApellidos(rs.getString(9));
                cliente.setTelefono(rs.getInt(10));
                cliente.setAfp(rs.getString(11));
                cliente.setSistemaSalud(rs.getString(12));
                cliente.setDireccion(rs.getString(13));
                cliente.setComuna(rs.getString(14));
                cliente.setEdad(rs.getInt(15));
                cliente.setCreated_at(rs.getDate(16));

                listaClientes.add(cliente);

            }
            rs.close();
            stm.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error: clase ClienteDAOimpl en el metodo listar");
            e.printStackTrace();
        }

        return listaClientes;
    }
}
