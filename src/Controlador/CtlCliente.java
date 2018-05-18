
package Controlador;

/**
 *
 * @author Cristian Sabogal L
 */

import Modelo.ClsConexion;
import Modelo.ClsCliente;
import DAO.ClienteDAO;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class CtlCliente {

    ClsConexion conexion = new ClsConexion();

    public CtlCliente() {

    }


    
     public boolean SolicitudGuardar(int id,String alias, String nombre, String apellido, String direccion, String poblacion, String telefono, String movil, String email, String observacion, Date fecha_nacimiento, String genero, String estadocivil, boolean desempleado, boolean fumador) {
        ClsCliente cliente = new ClsCliente(id, alias, apellido, nombre, direccion, poblacion, telefono, movil, email, observacion, genero, fecha_nacimiento, estadocivil, id, desempleado, fumador);
        ClienteDAO clientedao = new ClienteDAO();
        return clientedao.guardarCliente(cliente);
    }
    
    public boolean SolicitudGuardarDetalle( int id_docente, int id_estudiante,int id_materia){
        ClienteDAO cliDAO = new ClienteDAO();
        return cliDAO.guardardetalle(id_docente, id_estudiante, id_materia);
    }

 public ClsCliente SolicitudBuscar(int id) {
        ClienteDAO clientedao = new ClienteDAO();
        return clientedao.buscarCliente(id);
    }

public boolean SolicitudModificar(int id,String alias, String nombre, String apellido, String direccion, String poblacion, String telefono, String movil, String email, String observacion, Date fecha_nacimiento, String genero, String estadocivil, boolean desempleado, boolean fumador) {
        
        ClsCliente cliente = new ClsCliente(id, alias, apellido, nombre, direccion, poblacion, telefono, movil, email, observacion, genero, fecha_nacimiento, estadocivil, id, desempleado, fumador);
        ClienteDAO clientedao = new ClienteDAO();
        return clientedao.modificarCliente(cliente);
    }

    public boolean solicitudEliminar(int id_estudiante) {
        ClienteDAO cliDAO = new ClienteDAO();
        return cliDAO.eliminarEstudiante(id_estudiante);
    }

    public DefaultTableModel SolicitudListar() {
        ClienteDAO cliDAO = new ClienteDAO();
        return cliDAO.listarCliente();
    }
    
    public String SolicitudNombreMateria(int id_estudiante){
        ClienteDAO cliDAO= new ClienteDAO();
        return cliDAO.nombreMateria(id_estudiante);
    }
}
