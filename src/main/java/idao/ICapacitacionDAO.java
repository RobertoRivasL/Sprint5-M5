package idao;

import models.Capacitacion;

import java.util.List;

public interface ICapacitacionDAO {


    boolean registrar(Capacitacion capacitacion);

    boolean eliminar(Capacitacion capacitacion);

    boolean modificar(Capacitacion capacitacion);

    List<Capacitacion> listarCapacitacion();


}
