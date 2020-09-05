/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_de_oo;

/**
 *
 * @author RODRIGO
 */
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author nadia
 */

public class ConectaBanco {
    
    public static Connection conectabanco() throws ClassNotFoundException{
        
        try{
           Class.forName("org.postgresql.Driver");
           Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Tec","postgres", "postgres");
          // JOptionPane.showMessageDialog(null,"Conectado com sucesso ao banco de dados!");
           return con;
        }
        
        catch(SQLException error){
            JOptionPane.showMessageDialog(null,error);
            return null;
        }
    }
}
