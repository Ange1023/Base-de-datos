
package com.mycompany.basedatos1;
import static java.lang.reflect.Array.set;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class CMateria {

     int codigo;
    String carreraMat;
    String nombreMat;
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getcarreraMat() {
        return carreraMat;
    }

    public void setcarreraMat(String horasMat) {
        this.carreraMat = horasMat;
    }

    public String getNombreMat() {
        return nombreMat;
    }

    public void setNombreMat(String nombreMat) {
        this.nombreMat = nombreMat;
    }
   
    public void MostrarMateria (JTable paramTablaTotalMateria){
        CConexion objetoConexion = new CConexion ();
        
        DefaultTableModel modelo = new DefaultTableModel ();
        
        String sql = " ";
        
        modelo.addColumn("Id");
        modelo.addColumn("Carrera");
        modelo.addColumn("Materia");
        
        paramTablaTotalMateria.setModel(modelo);
        
        sql = "SELECT * FROM materia";
        
      String [] datos = new String [3];
      Statement st;
     
      
      try {
          st= objetoConexion.establecerConexion().createStatement();
          
          ResultSet rs = st.executeQuery(sql);
          
          while(rs.next()){
              datos[0]= rs.getString(1);
              datos[1]= rs.getString(2);
              datos[2]= rs.getString(3);
              
              modelo.addRow(datos);
          }
          paramTablaTotalMateria.setModel(modelo);
      }catch (Exception e){
          JOptionPane.showMessageDialog(null, "Error: "+ e.toString());
          
      }
    }
    
   public void insertarMateria (JTextField paramcarreraMat, JTextField paramNombreM){
       
       setcarreraMat(paramcarreraMat.getText());
       setNombreMat(paramNombreM.getText());
       
       CConexion objetoConexion = new CConexion ();
       
       String consulta = "INSERT INTO materia(carrera, nombre) VALUES (?,?);";
       try{
           CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
           cs.setString(1, getcarreraMat());
           cs.setString(2, getNombreMat());
           
           cs.execute();
           
           JOptionPane.showMessageDialog(null, "Se insertó correctamente");
       }catch (Exception e){
           JOptionPane.showMessageDialog(null, "Error: "+ e.toString());
       }
    }
   public void SeleccionarMateria(JTable paramTablaMateria, JTextField paramID, JTextField paramcarreraM, JTextField paramNombreM){
       
       try{
       int fila = paramTablaMateria.getSelectedRow();
       if (fila >= 0){
           paramID.setText(paramTablaMateria.getValueAt(fila, 0).toString());
           paramcarreraM.setText(paramTablaMateria.getValueAt(fila, 1).toString());
           paramNombreM.setText(paramTablaMateria.getValueAt(fila, 2).toString());
       }
       else{
           JOptionPane.showMessageDialog(null, "Fila no seleccionada");
       }
       
   }catch(Exception e){
       
       JOptionPane.showMessageDialog(null, "Error: "+ e.toString());
   }
       
 }
        public void ModificarMateria (JTextField paramID,JTextField paramcarreraMat, JTextField paramNombreM){
       setCodigo(Integer.parseInt(paramID.getText()));
       setcarreraMat(paramcarreraMat.getText());
       setNombreMat(paramNombreM.getText());
       
       CConexion objetoConexion = new CConexion ();
       
       String consulta = "UPDATE public.materia SET  carrera=?, nombre=? WHERE materia.clave_m= ? ;";
       try{
           CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
          
           cs.setString(1, getcarreraMat());
           cs.setString(2, getNombreMat());
           cs.setInt(3, getCodigo());
           
           cs.execute();
           
           JOptionPane.showMessageDialog(null, "Se insertó correctamente");
       }catch (Exception e){
           JOptionPane.showMessageDialog(null, "Error: "+ e.toString());
       }
    }
           public void EliminarMateria (JTextField paramID){
       setCodigo(Integer.parseInt(paramID.getText()));
       
       CConexion objetoConexion = new CConexion ();
       
       String consulta = "DELETE FROM materia WHERE materia.clave_m = ?";
       try{
           CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
          
           cs.setInt(1, getCodigo());
           
           cs.execute();
           
           JOptionPane.showMessageDialog(null, "Se eliminó correctamente");
       }catch (Exception e){
           JOptionPane.showMessageDialog(null, "Error: "+ e.toString());
       }
    }
        
 }

