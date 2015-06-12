/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.SalaCirurgia;

/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 */
public class SalaCirurgiaDAO {

    private String mensagem;

    public boolean Insere (SalaCirurgia slc){
        String sql = "insert into salacirurgia ( `desc`) values ( ?)";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {            
            //ps.setInt(1, slc.getCodSala());
            ps.setString(1, slc.getDesc());

            ps.execute();
            setMensagem("Inseriu sala_cirurgia com sucesso");
            return true;
        } catch (SQLException e) {
            setMensagem("Problemas ao inserir sala_cirurgia" + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
    }

     public boolean Altera (SalaCirurgia slc){

         String sql = "update salacirurgia set "+
                      " `desc` = ?"
                      +"where `codSala` = ?";
         
         PreparedStatement ps = Conexao.getPreparedStatement(sql);
         try {
           ps.setString(1, slc.getDesc());
           ps.setInt(2, slc.getCodSala());          

            ps.execute();
            setMensagem("Alterar sala_cirurgia com sucesso");
            return true;
        } catch (SQLException e) {
            setMensagem("Problemas ao alterar sala_cirurgia" + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
     }

     public boolean exclui (SalaCirurgia slc) {
        // Preparação do SQL para interagir com o banco
        String sql = "delete from salacirurgia where codSala = ?";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, slc.getCodSala());

            if (ps.executeUpdate() > 0){
                setMensagem("Excluiu sala_cirurgia com sucesso") ;
                return true;
            }

            setMensagem("Não encontrou sala_cirurgia");
        }catch (SQLException e){
           setMensagem("Problemas ao excluir sala_cirurgia" + e.getMessage());
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
    public SalaCirurgia localiza(int codSala){
        String sql = "select * from salacirurgia " +
                     "where codSala = ?";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);

        try {
            ps.setInt(1, codSala);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                SalaCirurgia slc = new SalaCirurgia();
                slc.setCodSala(rs.getInt("codSala"));
                slc.setDesc(rs.getString("desc"));
                setMensagem("sala_cirurgia localizado com sucesso");
                return (slc);
            }
            setMensagem("sala_cirurgia não encontrado");
        } catch (SQLException ex) {
            setMensagem("Problemas ao localizar sala_cirurgia. Erro de SQL " + ex.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }

        return (null);
    }

    public ArrayList<SalaCirurgia> lista(){
        String sql = "select * from salacirurgia ";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        ArrayList <SalaCirurgia> ls = new ArrayList<SalaCirurgia>();

        try {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                SalaCirurgia slc = new SalaCirurgia();
                slc.setCodSala(rs.getInt("codSala"));
                slc.setDesc(rs.getString("desc"));
                ls.add(slc);
            }
            if (ls.isEmpty()){
                setMensagem("Relação de sala_cirurgia vazia");
                return (ls);
            }
            setMensagem("Relação de sala_cirurgia gerada com sucesso");
            return (ls);
        } catch (SQLException ex) {
            setMensagem("Problemas ao gerar relação de sala_cirurgia. Erro de SQL " + ex.getMessage());
        } finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        
        return (null);
    }

     public int ultimaChave(){
        String sql = "select LAST_INSERT_ID() as ultima from salacirurgia ";

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

