
package DAO;

/**
 *
 * @author Cristian Sabogal L
 */

import Modelo.ClsConexion;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class PedidoDAO extends ClsConexion {

<<<<<<< HEAD
    public boolean guardarPedido( double precio) {
=======
    public boolean guardarPedido(double precio) {
>>>>>>> 8a853854c4e35a60edd15371ff5e68946c5479ef
        String consulta = "insert into pedidos(fecha,precio) values ( sysdate()," + precio+ ")";
        return super.ejecutar(consulta);
    }

    public boolean guardarDetallePedido(int id_producto, int id_pedido, int cantidad) {
        String consulta = "insert into detalle_pedidos values (" + id_producto + "," + id_pedido + "," + cantidad
                + ")";
        return super.ejecutar(consulta);
    }

    public int buscarIdPedido(Date fecha, double precio) {
        String consulta = "select id_pedido from pedidos where  fecha=' " + fecha + "' AND precio=" + precio;
        super.ejecutarRetorno(consulta);

        try {
            if (resultadoDB.next()) {
                return resultadoDB.getInt("id_pedido");
            }
        } catch (SQLException ex) {
            System.out.println("Esto se tosto");
            ex.printStackTrace();
        }
        return 0;
    }

    public DefaultTableModel listarPedidos() {

        DefaultTableModel modelTabla;
        String nombreColumnas[] = {"Id Pedido", "Fecha", "Precio"};
        modelTabla = new DefaultTableModel(new Object[][]{}, nombreColumnas);

        String consulta = "select * from pedidos";
        super.ejecutarRetorno(consulta);

        try {
            while (resultadoDB.next()) {
                modelTabla.addRow(new Object[]{
                    resultadoDB.getInt("id_pedido") + "",
                    resultadoDB.getDate("fecha").toString(),
                    resultadoDB.getDouble("precio") + ""

                });
            }

        } catch (SQLException ex) {
            System.out.println("Esto se tosto");
            ex.printStackTrace();
        }
        return modelTabla;
    }
    
    public DefaultTableModel buscarPedido(int id_pedido) {
        DefaultTableModel modelTabla;
        
        String nombreColumnas[] = {"Id pedido", "Fecha", "Precio"};
        
        modelTabla = new DefaultTableModel(new Object[][]{}, nombreColumnas);
        
        String consulta = "select * from pedidos where id_pedido = " +id_pedido ;
      
        super.ejecutarRetorno(consulta);
        
        Object[] fila = new Object[3];
        
        try {
            while (resultadoDB.next()) {
                
                fila[0] = resultadoDB.getInt("id_pedido")+"";
                fila[1] = resultadoDB.getDate("fecha")+"";
                fila[2] = resultadoDB.getDouble("precio")+"";
         
                
                
                modelTabla.addRow(fila);
            }

        } catch (Exception e) {
            System.out.println("esto se tosto");
            e.printStackTrace();
        }
        return modelTabla;

    }
    
    public boolean eliminarUsuario(int id_pedido) {
        String consulta = "delete from pedidos where id_pedido=" + id_pedido;
        return super.ejecutar(consulta);
    }

}
