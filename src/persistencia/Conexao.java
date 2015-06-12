/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * @author Eduardo
 * Modified by: rebonatto
 */

package persistencia;



import Uteis.Configuracoes;
import Uteis.Configuracoes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author rebonatto
 */

public class Conexao {

    private static Connection con;
    //private static ConfigBase cfb = new ConfigBase();

    public static Connection getConexao(){       
        if (con == null){
            //cfb.LeAjustaParametros();
            try{
                Class.forName(Configuracoes.driver);
            }
            catch (ClassNotFoundException e){
                JOptionPane.showMessageDialog(null, "Classe não encontrada");
                System.out.println("Classe não encontrada "+e.getMessage());
            }
            try {
                con = DriverManager.getConnection(Configuracoes.banco, Configuracoes.usuario, Configuracoes.senha);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro de sql " + ex.getMessage());
                System.out.println("Erro de sql " + ex.getMessage());
            }
        }
        return con;
    }

    public static PreparedStatement getPreparedStatement(String sql){
        PreparedStatement ps = null;
        con = getConexao();
        
        try {
            ps = con.prepareStatement(sql);
        } catch (SQLException ex) {
            System.out.println("Erro ao criar PreparedStatement " + ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao criar PreparedStatemed " + ex.getMessage());
        }
        return ps;
    }

}


