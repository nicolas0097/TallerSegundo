/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import Modelo.ClsConexion;
import Modelo.Venta;
import Modelo.ventaTem;
import Vista.DB;

/**
 *
 * @author nicolasgarcia
 */
public class ventasDAO extends ClsConexion {

    public static ArrayList<ventaTem> venta = new ArrayList<ventaTem>();
    ventaTem pt = new ventaTem();

    public boolean guardarTemp(String producto, int cantidad, int valor) {
        try {
            ventaTem ventaTe = new ventaTem(producto, cantidad, valor);
            venta.add(ventaTe);
            listarVenta();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public DefaultTableModel listarVenta() {

        DefaultTableModel modelTabla;
        String nombreColumnas[] = {"Producto", "Cantidad", "Precio"};
        modelTabla = new DefaultTableModel(new Object[][]{}, nombreColumnas);

        for (int i = 0; i < venta.size(); i++) {
            modelTabla.addRow(new Object[]{
                venta.get(i).getProducto(),
                venta.get(i).getCantidad(),
                venta.get(i).getValor()});

        }
        DB.tableVen.setModel(modelTabla);
        return modelTabla;

    }

    public boolean guardarVenta(Venta ven) {
        String consulta = "INSERT INTO tallerSegundo.ventas values ('" + ven.getIdVenta() + "','" + ven.getFecha_venta() + "','" + ven.getHora_venta() + "','" + ven.getValor_venta() + "','" + ven.getCliente() + "')";
        System.out.println(consulta);
        return super.ejecutar(consulta);
    }

    public int cont() {
        int precio = 0;
        for (int i = 0; i < DB.tableVen.getRowCount(); i++) {
            precio += (int) DB.tableVen.getValueAt(i, 2);

        }
        System.out.println(precio);
        return precio;
    }

    public DefaultTableModel listarVenta1() {

        DefaultTableModel modelTabla;
        String nombreColumnas[] = {"idVenta", "fecha-venta", "hora-venta", "precio", "cliente"};
        modelTabla = new DefaultTableModel(new Object[][]{}, nombreColumnas);

        String consulta = "Select * from tallerExcel.ventas";
        super.ejecutarRetorno(consulta);
        System.out.println(consulta + "");

        try {
            while (resultadoDB.next()) {

                modelTabla.addRow(new Object[]{
                    resultadoDB.getInt("idVenta"),
                    resultadoDB.getString("fecha_venta"),
                    resultadoDB.getString("hora_enta"),
                    resultadoDB.getInt("valor_venta"),
                    resultadoDB.getInt("cliente")});

            }
        } catch (SQLException ex) {
            System.out.println("Esto se tosto listar " + ex);
        }
        DB.tableVentas.setModel(modelTabla);
        return modelTabla;
    }
}
