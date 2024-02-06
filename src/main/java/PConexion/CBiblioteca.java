package PConexion;

import Vista.JFEstudiante;
import com.mycompany.biblioteca.JFBiblioteca;
import com.mysql.cj.protocol.Resultset;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CBiblioteca {
    public boolean validarUsuario(JTextField usuario, JPasswordField contrasenia){
        try {
            String consulta = "select * from Usuario where Usuario.ingresoUsuario = (?) and Usuario.ingresoContrasenia = (?);";
            ResultSet resultado = null;
            PreparedStatement ps = null;
            PConexion.Conexion conexion = new PConexion.Conexion();
            ps = conexion.establecerConexion().prepareStatement(consulta);
            String cadenaContrasenia = String.valueOf(contrasenia.getPassword());//porque getpassword es char?
            
            ps.setString(1, usuario.getText());
            ps.setString(2, cadenaContrasenia);
            
            resultado = ps.executeQuery();
            if(resultado.next()){
                JOptionPane.showMessageDialog(null, "Usuario Correcto");
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "Usuario Incorrecto, vuelva a intentarlo");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: "+e.toString());
        }   
        return false;
    }  
}
