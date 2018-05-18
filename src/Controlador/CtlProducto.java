
package Controlador;

/**
 *
 * @author Cristian Sabogal L
 */

import DAO.ProductoDAO;
import Modelo.ClsConexion;
import Modelo.ClsProducto;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class CtlProducto {
     ClsConexion conexion = new ClsConexion();

    public CtlProducto() {

    }

    public boolean SolicitudGuardar(int id_proroducto, String nombre, int id_proveedor, String categoria, int cantidad_unidad, double precio, int unidades_existencia, int unidades_pedido, boolean suspendido) {
        
        ClsProducto producto = new ClsProducto(id_proroducto, nombre, id_proveedor, categoria, cantidad_unidad, precio, unidades_existencia, unidades_pedido, suspendido);
        ProductoDAO produDAO = new ProductoDAO();
        return produDAO.guardarUsuario(producto);
    }

    public ClsProducto SolicitudBuscar(int id_producto) {
        ProductoDAO produDAO = new ProductoDAO();
        return produDAO.buscarProveedor(id_producto);
    }
    
   public DefaultTableModel SolicitudBuscarPorNombre(String nombre) {
        ProductoDAO produDAO = new ProductoDAO();
        return produDAO.buscarProductoPorNombre(nombre);
    }
   
    public DefaultTableModel SolicitudBuscarPorProveedor(String nombre) {
        ProductoDAO produDAO = new ProductoDAO();
        return produDAO.buscarProductoPorProveedor(nombre);
    }
    
       public DefaultTableModel SolicitudBuscarPorCategoria(String nombre) {
        ProductoDAO produDAO = new ProductoDAO();
        return produDAO.buscarProductoCategoria(nombre);
    }

    public boolean SolicitudModificar(int id_proroducto, String nombre, int id_proveedor, String categoria, int cantidad_unidad, double precio, int unidades_existencia, int unidades_pedido, boolean suspendido) {
        ClsProducto producto = new ClsProducto(id_proroducto, nombre, id_proveedor, categoria, cantidad_unidad, precio, unidades_existencia, unidades_pedido, suspendido);
        ProductoDAO produDAO = new ProductoDAO();
        ClsProducto a = SolicitudBuscar(id_proroducto);
        if (a.getNombre() != null) {
            a.setNombre(nombre);
            a.setId_proveedor(id_proveedor);
            a.setCategoria(categoria);
            a.setCantidad_unidad(cantidad_unidad);
            a.setPrecio(precio);
            a.setUnidades_existencia(unidades_existencia);
            a.setUnidades_pedido(unidades_pedido);
            a.setSuspendido(suspendido);
           
            return produDAO.modificarUsuario(a);
        }

        return false;

    }

    public boolean solicitudEliminar(int id_producto) {
        ProductoDAO produDAO = new ProductoDAO();
        return produDAO.eliminarProducto(id_producto);
    }

    public DefaultTableModel SolicitudListar() {
        ProductoDAO produDAO = new ProductoDAO();
        return produDAO.listarUsuario();
    }
    
    public ArrayList<String> solicitudNombres(){
        ProductoDAO produDAO= new ProductoDAO();
        return produDAO.listarNombres();
    }
    public int solicitudId(String nombre){
        ProductoDAO produDAO= new ProductoDAO();
        return produDAO.buscarMateriaPorNombre(nombre);
    }
    
   
}
