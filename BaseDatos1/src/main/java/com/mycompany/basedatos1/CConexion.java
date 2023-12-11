
package com.mycompany.basedatos1;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class CConexion {
    Connection conectar = null;
    String user = "postgres";
    String pw = "an1042004";
    String bd = "BaseDatosUniversidad";
    String ip = "localhost";
    String puerto = "5432";
    
    String cadena = "jdbc:postgresql://"+ip+":"+puerto+"/"+bd;
    public Connection establecerConexion(){
        
        try{
            Class.forName("org.postgresql.Driver");
            
            conectar= DriverManager.getConnection(cadena,user,pw);
            JOptionPane.showMessageDialog(null, "Se conectó correctamente a la base de datos");
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error: "+e.toString());
        }
        return conectar;
    }
}
