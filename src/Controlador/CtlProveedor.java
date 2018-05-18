
package Controlador;

/**
 *
 * @author Cristian Sabogal L
 */

import DAO.ProveedorDAO;
import Modelo.ClsConexion;
import Modelo.ClsProveedor;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class CtlProveedor {

    ClsConexion conexion = new ClsConexion();

    public CtlProveedor() {

    }

    public boolean SolicitudGuardar(int id_proveedor, String nombre, String direccion, String telefono, String fax, String rfc, String correo, String web, String contacto, String puesto_contacto, String telefono_contacto, String movil_contacto,
            String correo_contacto, String telefono_alt1, String telefono_alt2, int cantidad_comprada, double cantidad_$) {
        ClsProveedor proveedor = new ClsProveedor(id_proveedor, nombre, direccion, telefono, fax, rfc, correo, web, contacto, puesto_contacto, telefono_contacto, movil_contacto, correo_contacto, telefono_alt1, telefono_alt2, cantidad_comprada, cantidad_$);
        ProveedorDAO proveDAO = new ProveedorDAO();
        return proveDAO.guardarProveedor(proveedor);
    }

    public ClsProveedor SolicitudBuscar(int id_proveedor) {
        ProveedorDAO proveDAO = new ProveedorDAO();
        return proveDAO.buscarProveedor(id_proveedor);
    }

    public DefaultTableModel SolicitudBuscarPorNombre(String nombre) {
        ProveedorDAO produDAO = new ProveedorDAO();
        return produDAO.buscarProveedorPorNombre(nombre);
    }

    public boolean SolicitudModificarProveedor(int idProveedor, String nombre, String direccion, String teledono, String fax, String rfc, String correo, String web, String contacto, String telefonoContacto, String movilContacto, String puestoContacto,
            String correoContacto, String telefonoAlterno, String telefonoAlterno2, int cantidadCompradas, double cantidadEnPesos) {
        ClsProveedor proveedor = new ClsProveedor(idProveedor, nombre, direccion, teledono, fax, rfc, correo, web, contacto, puestoContacto, telefonoContacto, movilContacto, correoContacto, telefonoAlterno, telefonoAlterno2, cantidadCompradas, cantidadEnPesos);
        ProveedorDAO proveDAO = new ProveedorDAO();
        return proveDAO.modificarProveedor(proveedor);
    }

    public boolean solicitudEliminar(int id_docente) {
        ProveedorDAO proveDAO = new ProveedorDAO();
        return proveDAO.eliminarUsuario(id_docente);
    }

    public DefaultTableModel SolicitudListar() {
        ProveedorDAO proveDAO = new ProveedorDAO();
        return proveDAO.listarProveedor();
    }

    public ArrayList<String> SolicitudNombres() {
        ProveedorDAO proveDAO = new ProveedorDAO();
        return proveDAO.listarNombres();
    }

    public int solicitudId(String nombre) {
        ProveedorDAO mateDAO = new ProveedorDAO();
        return mateDAO.buscarDocentePorNombre(nombre);
    }

    public String SolicitudNombreProveedor(int id_proveedor) {
        ProveedorDAO proveDAO = new ProveedorDAO();
        return proveDAO.nombreProveedor(id_proveedor);
    }
}
