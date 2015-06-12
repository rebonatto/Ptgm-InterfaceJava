/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Tipo;


/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 */
public class TipoDAO{

    private String mensagem;

    public boolean Insere (Tipo tp){
        String sql = "insert into tipo (`desc`) values (?)";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            //ps.setInt(1, tp.getCodTipo());
            ps.setString(1, tp.getDesc());

            ps.execute();
            setMensagem("Inseriu Tipo com sucesso");
            return true;
        } catch (SQLException e) {
            setMensagem("Problemas ao inserir Tipo " + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
    }

    public boolean Altera (Tipo tp){

         String sql = "update tipo set "+
                      "`desc` = ?"
                      +"where `codTipo` = ?";
         
         PreparedStatement ps = Conexao.getPreparedStatement(sql);
         try {
            ps.setInt(2, tp.getCodTipo());
            ps.setString(1, tp.getDesc());

            ps.execute();
            setMensagem("Alterar Tipo com sucesso");
            return true;
        } catch (SQLException e) {
            setMensagem("Problemas ao alterar Tipo " + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
     }

     public boolean exclui (Tipo tp) {
        // Preparação do SQL para interagir com o banco
        String sql = "delete from tipo where codTipo = ?";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, tp.getCodTipo());

            if (ps.executeUpdate() > 0){
                setMensagem("Excluiu Tipo com sucesso") ;
                return true;
            }

            setMensagem("Não encontrou Tipo");
        }catch (SQLException e){
           setMensagem("Problemas ao excluir Tipo " + e.getMessage());
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
    public Tipo localiza(int codTipo){
        String sql = "select * from tipo " +
                     "where codTipo = ?";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);

        try {
            ps.setInt(1, codTipo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Tipo tp = new Tipo();
                tp.setCodTipo(rs.getInt("codTipo"));
                tp.setDesc(rs.getString("desc"));
                setMensagem("Tipo localizado com sucesso");
                return (tp);
            }
            setMensagem("Tipo não encontrado");
        } catch (SQLException ex) {
            setMensagem("Problemas ao localizar Tipo. Erro de SQL " + ex.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }

        return (null);
    }

    public ArrayList<Tipo> lista(){
        String sql = "select * from tipo ";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        ArrayList <Tipo> ls = new ArrayList<Tipo>();


        try {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Tipo tp = new Tipo();
                tp.setCodTipo(rs.getInt("codTipo"));
                tp.setDesc(rs.getString("desc"));
                ls.add(tp);
            }
            if (ls.isEmpty()){
                setMensagem("Relação de Tipo vazia");
                return (ls);
            }
            setMensagem("Relação de Tipo gerada com sucesso");
            return (ls);
        } catch (SQLException ex) {
            setMensagem("Problemas ao gerar relação de Tipo. Erro de SQL " + ex.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return (null);
    }

     public int ultimaChave(){
        String sql = "select LAST_INSERT_ID() as ultima from tipo ";

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
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
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

