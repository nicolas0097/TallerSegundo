
package Modelo;

/**
 *
 * @author Cristian Sabogal L
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClsConexion {

    protected String driver = "org.gjt.mm.mysql.Driver"; //nombre del driver
    protected String connectString = "jdbc:mysql://localhost/tallersegundo"; //ubicacion de la base de datos, para postgres esta es por defecto
    protected String user = "root"; //usuario de la base de datos
    protected String password = ""; //password de la base de datos
    protected Connection conexionDB; // variable que permite la conexion
    protected Statement sentenciaSQL; //permite la ejecucion de sentencias SQL
    protected ResultSet resultadoDB;//almacena el resultado de una consulta

    /**
     * Permite la conexion de la base de datos
    */
    public void conectar() {
        try {
            Class.forName(driver); //se carga el driver en memoria
            conexionDB = DriverManager.getConnection(connectString, user, password);//conexion a la base de datos
            sentenciaSQL = conexionDB.createStatement();//variable que permite ejecutar las sentencias SQL                                
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Desconecta la conexion de la base de datos
     */
    public void desconectar() {
        try {
            conexionDB.close();//cierra conexion
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean ejecutar(String sentencia) {
        try {
            conectar();
             System.out.println(sentencia);
            sentenciaSQL.executeUpdate(sentencia);
           
        } catch (Exception e) {
            e.printStackTrace();
            return false;
            
        }
        return true;
    }

    public void ejecutarRetorno(String sentencia) {
        try {
            conectar();
            resultadoDB = sentenciaSQL.executeQuery(sentencia);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
