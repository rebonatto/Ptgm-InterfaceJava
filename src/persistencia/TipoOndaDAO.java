/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.TipoOnda;

/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 */
public class TipoOndaDAO {

    private String mensagem;

    public boolean Insere (TipoOnda tpo){
        String sql = "insert into tipoonda (`desc`) values (?)";
        
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            //ps.setInt(1, tpo.getCodTponda());
            ps.setString(1, tpo.getDesc());

            ps.execute();
            setMensagem("Inseriu tipoOnda com sucesso");
            return true;
        } catch (SQLException e) {
            setMensagem("Problemas ao inserir tipoOnda " + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
    }

     public boolean Altera (TipoOnda tpo){

         String sql = "update tipoonda set "+
                      " `desc` = ? where `codTipoOnda` = ?";
         
         PreparedStatement ps = Conexao.getPreparedStatement(sql);
         try {
            ps.setInt(2, tpo.getCodTipoOnda());
            ps.setString(1, tpo.getDesc());

            ps.execute();
            setMensagem("Inseriu tipoOnda com sucesso");
            return true;
        } catch (SQLException e) {
            setMensagem("Problemas ao inserir tipoOnda " + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
     }

     public boolean exclui (TipoOnda tip) {
        // Preparação do SQL para interagir com o banco
        String sql = "delete from tipoonda where codTipoOnda = ?";
        
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, tip.getCodTipoOnda());

            if (ps.executeUpdate() > 0){
                setMensagem("Excluiu tipoOnda com sucesso") ;
                return true;
            }

            setMensagem("Não encontrou tipoOnda");
        }catch (SQLException e){
           setMensagem("Problemas ao excluir tipoOnda " + e.getMessage());
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
    public TipoOnda localiza(int codTipoOnda){
        String sql = "select * from tipoonda " +
                     "where codTipoOnda = ?";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);

        try {
            ps.setInt(1, codTipoOnda);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                TipoOnda mca = new TipoOnda();
                mca.setCodTipoOnda(rs.getInt("codTipoOnda"));
                mca.setDesc(rs.getString("desc"));
                setMensagem("Tipo onda localizado com sucesso");
                return (mca);
            }
            setMensagem("Tipo onda não encontrado");
        } catch (SQLException ex) {
            setMensagem("Problemas ao localizar Tipo onda. Erro de SQL " + ex.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }

        return (null);
    }

    public ArrayList<TipoOnda> lista(){
        String sql = "select * from tipoonda ";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        ArrayList <TipoOnda> ls = new ArrayList<TipoOnda>();


        try {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                TipoOnda tpo = new TipoOnda();
                tpo.setCodTipoOnda(rs.getInt("codTipoOnda"));
                tpo.setDesc(rs.getString("desc"));
                ls.add(tpo);
            }
            if (ls.isEmpty()){
                setMensagem("Relação de tipoOnda vazia");
                return (ls);
            }
            setMensagem("Relação de tipoOnda gerada com sucesso");
            return (ls);
        } catch (SQLException ex) {
            setMensagem("Problemas ao gerar relação de tipoOnda. Erro de SQL " + ex.getMessage());
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
        String sql = "select LAST_INSERT_ID() as ultima from tipoonda ";

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
