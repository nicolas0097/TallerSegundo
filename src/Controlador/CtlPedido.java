
package Controlador;

/**
 *
 * @author Cristian Sabogal L
 */

import DAO.PedidoDAO;
import Modelo.ClsConexion;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class CtlPedido {
    ClsConexion conexion = new ClsConexion();

    public CtlPedido() {
    }
    
    public boolean solicitudGuardar(double precio){
        PedidoDAO pediDAO= new PedidoDAO();
        return pediDAO.guardarPedido(precio);
    }
     public boolean solicitudGuardarDetalle(int id_producto,int id_pedido, int cantidad){
        PedidoDAO pediDAO= new PedidoDAO();
        return pediDAO.guardarDetallePedido(id_producto,id_pedido, cantidad);
    }
     
     public int solicitudIdPedido( Date fecha, double precio){
         PedidoDAO pediDAO= new PedidoDAO();
         return pediDAO.buscarIdPedido(fecha, precio);
     }
     
     public DefaultTableModel solicitudListar(){
         PedidoDAO pediDAO = new PedidoDAO();
         return pediDAO.listarPedidos();
     }
     
       public DefaultTableModel solicituBuscar(int id_pedido){
         PedidoDAO pediDAO = new PedidoDAO();
         return pediDAO.buscarPedido(id_pedido);
     }
           public boolean solicitudEliminar(int id_pedido) {
        PedidoDAO pediDAO = new PedidoDAO();
        return pediDAO.eliminarUsuario(id_pedido);
    }
}
