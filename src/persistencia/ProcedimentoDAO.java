

package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Procedimento;


/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 */
public class ProcedimentoDAO {

    private String mensagem;

    public boolean Insere (Procedimento proced){
        String sql = "insert into procedimento (`desc`) values (?)";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {            
            //ps.setInt(1, proced.getCodProced());
            ps.setString(1, proced.getDesc());

            ps.execute();
            setMensagem("Inseriu Procedimento com sucesso");
            return true;
        } catch (SQLException e) {
            setMensagem("Problemas ao inserir Procedimento" + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
    }

     public boolean Altera (Procedimento proced){

         String sql = "update procedimento set "+
                      "`desc` = ?"
                      +"where `codProced` = ?";

         PreparedStatement ps = Conexao.getPreparedStatement(sql);
         try {
            ps.setInt(2, proced.getCodProced());
            ps.setString(1, proced.getDesc());

            ps.execute();
            setMensagem("Inseriu Procedimento com sucesso");
            return true;
        } catch (SQLException e) {
            setMensagem("Problemas ao inserir Procedimento" + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
     }

     public boolean exclui (Procedimento proced) {
        // Preparação do SQL para interagir com o banco
        String sql = "delete from procedimento where codProced = ?";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, proced.getCodProced());

            if (ps.executeUpdate() > 0){
                setMensagem("Excluiu Procedimento com sucesso") ;
                return true;
            }

            setMensagem("Não encontrou Procedimento");
        }catch (SQLException e){
           setMensagem("Problemas ao excluir Procedimento" + e.getMessage());
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
    public Procedimento localiza(int codProced){
        String sql = "select * from procedimento " +
                     "where codProced = ?";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);

        try {
            ps.setInt(1, codProced);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Procedimento proced = new Procedimento();
                proced.setCodProced(rs.getInt("codProced"));
                proced.setDesc(rs.getString("desc"));
                setMensagem("Procedimento localizado com sucesso");
                return (proced);
            }
            setMensagem("Eventp não encontrado");
        } catch (SQLException ex) {
            setMensagem("Problemas ao localizar Procedimento. Erro de SQL " + ex.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return (null);
    }

    public ArrayList<Procedimento> lista(){
        String sql = "select * from procedimento ";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        ArrayList <Procedimento> ls = new ArrayList<Procedimento>();

        try {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Procedimento proced = new Procedimento();
                proced.setCodProced(rs.getInt("codProced"));
                proced.setDesc(rs.getString("desc"));
                ls.add(proced);
            }
            if (ls.isEmpty()){
                setMensagem("Relação de Procedimento vazia");
                return (ls);
            }
            setMensagem("Relação de Procedimento gerada com sucesso");
            return (ls);
        } catch (SQLException ex) {
            setMensagem("Problemas ao gerar relação de Procedimento. Erro de SQL " + ex.getMessage());
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
        String sql = "select LAST_INSERT_ID() as ultima from procedimento ";

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
            setMensagem("Problemas ao localizar Chave em Procedimento. Erro de SQL " + ex.getMessage());
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
