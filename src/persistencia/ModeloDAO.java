/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Modelo;

/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 */
public class ModeloDAO {
private String mensagem;
    //sem auto increment
    public boolean Insere (Modelo mdl){
        String sql = "insert into modelo (`desc`) values (?)";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        
        try {            
            //ps.setInt(1, mdl.getCodModelo());
            ps.setString(1, mdl.getDesc());

            ps.execute();
            setMensagem("Inseriu Modelo com sucesso");
            return true;
        } catch (SQLException e) {
            setMensagem("Problemas ao inserir Modelo " + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
    }

     public boolean Altera (Modelo mdl){

         String sql = "update modelo set "+
                      "`desc` = ? where `codModelo` = ?";
         PreparedStatement ps = Conexao.getPreparedStatement(sql);
         
         try {           
            ps.setInt(2, mdl.getCodModelo());
            ps.setString(1, mdl.getDesc());

            ps.execute();
            setMensagem("Inseriu Modelo com sucesso");
            return true;
        } catch (SQLException e) {
            setMensagem("Problemas ao inserir Modelo " + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
     }

     public boolean exclui (Modelo mdl) {
        // Preparação do SQL para interagir com o banco
        String sql = "delete from modelo where codModelo = ?";
        
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        
        try {
            ps.setInt(1, mdl.getCodModelo());

            if (ps.executeUpdate() > 0){
                setMensagem("Excluiu Modelo com sucesso") ;
                return true;
            }

            setMensagem("Não encontrou Modelo");
        }catch (SQLException e){
           setMensagem("Problemas ao excluir Modelo " + e.getMessage());
       }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
       return false;
    }

    // Receber por parametro a chave primária
    public Modelo localiza(int codModelo){
        String sql = "select * from modelo " +
                     "where codModelo = ?";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);

        try {
            ps.setInt(1, codModelo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Modelo mdl = new Modelo();
                mdl.setCodModelo(rs.getInt("codModelo"));
                mdl.setDesc(rs.getString("desc"));
                setMensagem("Modelo localizado com sucesso");
                return (mdl);
            }
            setMensagem("Modelo não encontrado");
        } catch (SQLException ex) {
            setMensagem("Problemas ao localizar Modelo. Erro de SQL " + ex.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }

        return (null);
    }

    public ArrayList<Modelo> lista(){
        String sql = "select * from modelo ";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        ArrayList <Modelo> ls = new ArrayList<Modelo>();


        try {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Modelo mdl = new Modelo();
                mdl.setCodModelo(rs.getInt("codModelo"));
                mdl.setDesc(rs.getString("desc"));
                ls.add(mdl);
            }
            if (ls.isEmpty()){
                setMensagem("Relação de Modelo vazia");
                return (ls);
            }
            setMensagem("Relação de Modelo gerada com sucesso");
            return (ls);
        } catch (SQLException ex) {
            setMensagem("Problemas ao gerar relação de Modelo. Erro de SQL " + ex.getMessage());
        }
        return (null);
    }

     public int ultimaChave(){
        String sql = "select LAST_INSERT_ID() as ultima from modelo ";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);

        try {
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int ret = rs.getInt("ultima");
                setMensagem("Chave localizada com sucesso");
                return (ret);
            }
            setMensagem("Chave não localizada");
        } catch (SQLException ex) {
            setMensagem("Problemas ao localizar Chave em Marca. Erro de SQL " + ex.getMessage());
        }
        return (0);
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
