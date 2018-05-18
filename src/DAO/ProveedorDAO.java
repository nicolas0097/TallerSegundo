
package DAO;

/**
 *
 * @author Cristian Sabogal L
 */

import Modelo.ClsConexion;

import Modelo.ClsProveedor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class ProveedorDAO extends ClsConexion {

    ClsProveedor dao = new ClsProveedor();

    public boolean guardarProveedor(ClsProveedor proveDAO) {
        String consulta = "insert into proveedores values("
                + proveDAO.getId_proveedor() + ",'" + proveDAO.getNombre() + "','" + proveDAO.getDireccion() + "','" + proveDAO.getTelefono() + "','"
                + proveDAO.getFax() + "','" + proveDAO.getRfc() + "','" + proveDAO.getCorreo() + "','" + proveDAO.getWeb() + "','" + proveDAO.getContacto() + "','" + proveDAO.getPuesto_contacto()
                + "','" + proveDAO.getTelefono_contacto() + "','" + proveDAO.getMovil_contacto() + "','" + proveDAO.getCorreo_contacto() + "','" + proveDAO.getTelefono_alt1()
                + "','" + proveDAO.getTelefono_alt2() + "'," + proveDAO.getCantidad_comprada() + "," + proveDAO.getCantidad_$() + ")";
        return super.ejecutar(consulta);
    }

    public ClsProveedor buscarProveedor(int id) {
        String consulta = "select * from proveedores where id_proveedor=" + id + "";

        super.ejecutarRetorno(consulta);
        try {
            if (resultadoDB.next()) {
                dao.setId_proveedor(resultadoDB.getInt("id_proveedor"));
                dao.setNombre(resultadoDB.getString("nombre"));
                dao.setDireccion(resultadoDB.getString("direccion"));
                dao.setTelefono(resultadoDB.getString("telefono"));
                dao.setFax(resultadoDB.getString("fax"));
                dao.setRfc(resultadoDB.getString("rfc"));
                dao.setCorreo(resultadoDB.getString("correo"));
                dao.setWeb(resultadoDB.getString("web"));
                dao.setContacto(resultadoDB.getString("contacto"));
                dao.setPuesto_contacto(resultadoDB.getString("puesto_contacto"));
                dao.setTelefono_contacto(resultadoDB.getString("telefono_contacto"));
                dao.setMovil_contacto(resultadoDB.getString("movil_contacto"));
                dao.setCorreo_contacto(resultadoDB.getString("correo_contacto"));
                dao.setTelefono_alt1(resultadoDB.getString("telefono_alt1"));
                dao.setTelefono_alt2(resultadoDB.getString("telefono_alt2"));
                dao.setCantidad_comprada(resultadoDB.getInt("cantidad_comprada"));
                dao.setCantidad_$(resultadoDB.getDouble("cantidad_$"));
            }
        } catch (SQLException ex) {
            System.out.println("Esto se tosto");
            ex.printStackTrace();
        }
        return dao;
    }

    public DefaultTableModel buscarProveedorPorNombre(String nombre) {
        DefaultTableModel modelTabla;
        
        String nombreColumnas[] = {"Nr Proveedor", "Nombre", "Direccion", "Telefono", "Fax", "RFC",
            "Correo", "Web", "Contacto", "Puesto Contacto", "Telefono Contacto", "Movil Contacto",
            "Correo Contacto", "Telefono Alterno 1", "Telefono Alterno 2", "Cantidades Compradas", "Cantidades en $"};
        
        modelTabla = new DefaultTableModel(new Object[][]{}, nombreColumnas);
        
        String consulta = "select * from proveedores where nombre like " + "'" + nombre + "_%'";
      
        super.ejecutarRetorno(consulta);
        
        Object[] fila = new Object[17];
        
        try {
            while (resultadoDB.next()) {
                
                fila[0] = resultadoDB.getString("id_proveedor");
                fila[1] = resultadoDB.getString("nombre");
                fila[2] = resultadoDB.getString("direccion");
                fila[3] = resultadoDB.getString("telefono");
                fila[4] = resultadoDB.getString("fax");
                fila[5] = resultadoDB.getString("rfc");
                fila[6] = resultadoDB.getString("correo");
                fila[7] = resultadoDB.getString("web");
                fila[8] = resultadoDB.getString("contacto");
                fila[9] = resultadoDB.getString("puesto_contacto");
                fila[10] = resultadoDB.getString("telefono_contacto");
                fila[11] = resultadoDB.getString("movil_contacto");
                fila[12] = resultadoDB.getString("correo_contacto");
                fila[13] = resultadoDB.getString("telefono_alterno1");
                fila[14] = resultadoDB.getString("telefono_alterno2");
                fila[15] = resultadoDB.getInt("cantidade_comprada");
                fila[16] = resultadoDB.getInt("cantidade_$");
                
                
                modelTabla.addRow(fila);
            }

        } catch (Exception e) {
            System.out.println("esto se tosto");
            e.printStackTrace();
        }
        return modelTabla;

    }

    public boolean modificarProveedor(ClsProveedor proveDAO) {
        String consulta = "update docentes set nombre_docente='" + proveDAO.getNombre() + "',direccion='" + proveDAO.getDireccion()
                + "'," + "telefono= '" + proveDAO.getTelefono() + "', fax='" + proveDAO.getFax() + "', rfc='" + proveDAO.getRfc()
                + "', correo='" + proveDAO.getCorreo() + "', web='" + proveDAO.getWeb() + "', contacto='" + proveDAO.getContacto() + "', puesto_contacto='"
                + proveDAO.getPuesto_contacto() + "', telefono_contacto='" + proveDAO.getTelefono_contacto() + "', movil_contacto='" + proveDAO.getMovil_contacto()
                + "', correo_contacto='" + proveDAO.getCorreo_contacto() + "', telefono_alt1='" + proveDAO.getTelefono_alt1() + "', telefono_alt2='"
                + proveDAO.getTelefono_alt2() + "', cantidad_comprada=" + proveDAO.getCantidad_comprada() + ", cantidad_$=" + proveDAO.getCantidad_$()
                + " where id_docente= " + proveDAO.getId_proveedor();
        return super.ejecutar(consulta);
    }

    public boolean eliminarUsuario(int id_proveedor) {
        String consulta = "delete from proveedores where id_proveedor=" + id_proveedor;
        return super.ejecutar(consulta);
    }

    public DefaultTableModel listarProveedor() {

        DefaultTableModel modelTabla;
        String nombreColumnas[] = {"Nr Proveedor", "Nombre", "Direccion", "Telefono", "Fax", "RFC","Correo"
                ,"Web","Contacto","Puesto Contacto","Telefono Contacto","Movil Contacto"
                ,"Correo Contacto","Telefono Alterno 1","Telefono Alterno 2","Cantidades Compradas","Cantidades en $"};
        modelTabla = new DefaultTableModel(new Object[][]{}, nombreColumnas);
        
        
        String consulta = "select * from proveedores";
        super.ejecutarRetorno(consulta);

        try {
            while (resultadoDB.next()) {
                modelTabla.addRow(new Object[]{
                    resultadoDB.getInt("id_proveedor")+"",
                    resultadoDB.getString("nombre"),
                    resultadoDB.getString("direccion"),
                    resultadoDB.getString("telefono"),
                    resultadoDB.getString("fax"),
                    resultadoDB.getString("rfc"),
                    resultadoDB.getString("correo"),
                    resultadoDB.getString("web"),
                    resultadoDB.getString("contacto"),
                    resultadoDB.getString("puesto_contacto"),
                    resultadoDB.getString("telefono_contacto"),
                    resultadoDB.getString("movil_contacto"),
                    resultadoDB.getString("correo_contacto"),
                    resultadoDB.getString("telefono_alterno1"),
                    resultadoDB.getString("telefono_alterno2"),
                    resultadoDB.getInt("cantidad_comprada")+"",
                    resultadoDB.getDouble("cantidad_$")+""});
            }
         
        } catch (SQLException ex) {
            System.out.println("Esto se tosto");
            ex.printStackTrace();
        }
        return modelTabla;
    }

    public ArrayList<String> listarNombres() {
        ArrayList<String> lista = new ArrayList<>();
        String consulta = "select nombre from proveedores";
        super.ejecutarRetorno(consulta);
        try {
            while (resultadoDB.next()) {
                lista.add(resultadoDB.getString("nombre"));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public int buscarDocentePorNombre(String nombre) {
        String consulta = "select id_proveedor from proveedores where nombre = '" + nombre + "'";
        super.ejecutarRetorno(consulta);
        try {
            while (resultadoDB.next()) {
                return resultadoDB.getInt("id_proveedor");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return 0;
    }
    
      public String nombreProveedor(int id) {
        String consulta = "select proveedores.nombre from productos join proveedores on productos.id_proveedor=proveedores.id_proveedor where productos.id_producto = " + id;
        super.ejecutarRetorno(consulta);
        String r = "";
        try {
            while (resultadoDB.next()) {
                return resultadoDB.getString("proveedores.nombre");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
}
