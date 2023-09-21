package idao;

import models.Cliente;

import java.util.List;

public interface IClienteDAO {


    boolean registrar(Cliente cliente);

    boolean eliminar(Cliente cliente);

    boolean modificar(Cliente cliente);

    List<Cliente> listarClientes();

}
