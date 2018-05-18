
package DAO;

/**
 *
 * @author Cristian Sabogal L
 */

import Modelo.ClsCliente;
import Modelo.ClsConexion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class ClienteDAO extends ClsConexion {

    ClsCliente dao = new ClsCliente();

    public boolean guardarCliente(ClsCliente clientedao) {
        String consulta = "insert into clientes values(" + clientedao.getId_cliente() + ",'"
                + clientedao.getAlias() + "','" + clientedao.getApellido() + "','" + clientedao.getNombre() + "','" + clientedao.getDireccion() + "','" + clientedao.getPoblacion()
                + "','" + clientedao.getTelefono() + "','" + clientedao.getMovil() + "','" + clientedao.getEmail() + "','" + clientedao.getObservacion() + "','" + clientedao.getSexo() + "','" + clientedao.getFecha_nacimiento() + "','" + clientedao.getEstado_civil() + "'," + clientedao.isDesempleado() + "," + clientedao.isFumador() + ")";
       
        return super.ejecutar(consulta);
    }

    public boolean guardardetalle(int id_docente, int id_estudiante, int id_materia) {
        String consulta = "insert into detalle_materias_docentes(id_docente,id_estudiante,id_materia) values( " + id_docente + "," + id_estudiante + "," + id_materia + ")";
        return super.ejecutar(consulta);

    }
    public boolean modificarCliente(ClsCliente cliDAO) {
        String consulta = "update clientes set alias='" + cliDAO.getAlias() + "',apellidos='" + cliDAO.getApellido()
                + "'," + "nombre= '" + cliDAO.getNombre() + "'," + "direccion='" + cliDAO.getDireccion() + "', poblacion='" + cliDAO.getPoblacion() + "', telefono='" + cliDAO.getTelefono() + "', movil='"
                + cliDAO.getMovil() + "', email='" + cliDAO.getEmail() + "', observaciones='" + cliDAO.getObservacion() + "', sexo='" + cliDAO.getSexo() + "', fecha_nacimiento='"
                + cliDAO.getFecha_nacimiento() + "', estado_civil='" + cliDAO.getEstado_civil() + "', desempleado=" + cliDAO.isDesempleado() + ", fumador= " + cliDAO.isFumador()
                + " where id_cliente= " + cliDAO.getId_cliente();
        return super.ejecutar(consulta);
    }

    public ClsCliente buscarCliente(int id) {
        String consulta = "select * from clientes where id_cliente=" + id + "";
        super.ejecutarRetorno(consulta);
        try {
            if (resultadoDB.next()) {
                dao.setId_cliente(Integer.parseInt(resultadoDB.getString("id_cliente")));
                dao.setAlias(resultadoDB.getString("alias"));
                dao.setNombre(resultadoDB.getString("nombre"));
                dao.setApellido(resultadoDB.getString("apellidos"));
                dao.setDireccion(resultadoDB.getString("direccion"));
                dao.setPoblacion(resultadoDB.getString("poblacion"));
                dao.setTelefono(resultadoDB.getString("telefono"));
                dao.setMovil(resultadoDB.getString("movil"));
                dao.setEmail(resultadoDB.getString("email"));
                dao.setObservacion(resultadoDB.getString("observaciones"));
                dao.setSexo(resultadoDB.getString("sexo"));
                dao.setEstado_civil(resultadoDB.getString("estado_civil"));
                dao.setFecha_nacimiento(resultadoDB.getDate("fecha_nacimiento"));
                boolean fumador = Boolean.parseBoolean(resultadoDB.getString("fumador"));
                boolean desempleado = Boolean.parseBoolean(resultadoDB.getString("desempleado"));
                dao.setDesempleado(desempleado);
                dao.setFumador(fumador);

            }
        } catch (SQLException ex) {
            System.out.println("Esto se tosto");
        }
        if (dao == null) {
            System.out.println("gafas marica");
        }
        return dao;
    }

    public boolean eliminarEstudiante(int id_cliente) {
        String consulta = "delete from clientes where id_cliente=" + id_cliente;
        return super.ejecutar(consulta);
    }

    public DefaultTableModel listarCliente() {
        DefaultTableModel modelTabla;
        String nombreColumnas[] = {"Id", "Alias", "Apellido", "Nombre", "Direccion", "Poblacion", "Telefono", "Movil", "Email", "Observacion", "Sexo", "Fecha Nacimiento", "Estado Civil", "Desempleado", "Fumador"};
        modelTabla = new DefaultTableModel(new Object[][]{}, nombreColumnas);

        String consulta = "select * from clientes";
    
        super.ejecutarRetorno(consulta);

        try {
            while (resultadoDB.next()) {
                modelTabla.addRow(new Object[]{
                    resultadoDB.getInt("id_cliente")+"",
                    resultadoDB.getString("alias"),
                    resultadoDB.getString("apellidos"),
                    resultadoDB.getString("nombre"),
                    resultadoDB.getString("direccion"),
                    resultadoDB.getString("poblacion"),
                    resultadoDB.getString("telefono"),
                    resultadoDB.getString("movil"),
                    resultadoDB.getString("email"),
                    resultadoDB.getString("observaciones"),
                    resultadoDB.getString("sexo"),
                    resultadoDB.getDate("fecha_nacimiento")+"",
                    resultadoDB.getString("estado_civil"),
                    resultadoDB.getBoolean("desempleado")+"",
                    resultadoDB.getBoolean("fumador")+""
                    });
            }
        } catch (SQLException ex) {
            System.out.println("Esto se tosto");
            ex.printStackTrace();
        }
        return modelTabla;
    }

    public String nombreMateria(int id_cliente) {
        String consulta = "select nombre_materia from estudiantes join materias on estudiantes.id_materia = materias.id_materia where estudiantes.id_estudiante = " + id_cliente;
        super.ejecutarRetorno(consulta);
        try {
            while (resultadoDB.next()) {
                return resultadoDB.getString("nombre_materia");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
