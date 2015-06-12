/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.TipoPadrao;

/**
 *
 * @author rebonatto
 */
public class TipoPadraoDAO {
    private String mensagem;

    public boolean Insere (TipoPadrao tpo){
        String sql = "insert into tipopadrao (`desc`) values (?)";
        
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {           
            ps.setString(1, tpo.getDesc());

            ps.execute();
            setMensagem("Inseriu Tipo de Padrao de Onda com sucesso");
            return true;
        } catch (SQLException e) {
            setMensagem("Problemas ao inserir TipoPadrao " + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
    }

     public boolean Altera (TipoPadrao tpo){

         String sql = "update tipopadrao set " +
                      " `desc` = ? where `codTipoPadrao` = ? ";
         
         PreparedStatement ps = Conexao.getPreparedStatement(sql);
         try {
           ps.setString(1, tpo.getDesc()); 
           ps.setInt(2, tpo.getCodTipoPadrao());            

            ps.execute();
            setMensagem("Inseriu Tipo de Padrao de Onda com sucesso");
            return true;
        } catch (SQLException e) {
            setMensagem("Problemas ao inserir TipoPadrao " + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
     }

     public boolean exclui (TipoPadrao tip) {
        // Preparação do SQL para interagir com o banco
        String sql = "delete from tipopadrao where codTipoPadrao = ?";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, tip.getCodTipoPadrao());

            if (ps.executeUpdate() > 0){
                setMensagem("Excluiu TipoPadrao com sucesso") ;
                return true;
            }

            setMensagem("Não encontrou Tipo Padrao");
        }catch (SQLException e){
           setMensagem("Problemas ao excluir Tipo Padrao " + e.getMessage());
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
    public TipoPadrao localiza(int codTipoPadrao){
        String sql = "select * from tipopadrao " +
                     "where codTipoPadrao = ?";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);

        try {
            ps.setInt(1, codTipoPadrao);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                TipoPadrao mca = new TipoPadrao();
                mca.setCodTipoPadrao(rs.getInt("codTipoPadrao"));
                mca.setDesc(rs.getString("desc"));
                setMensagem("Tipo de Padrao de Onda localizado com sucesso");
                return (mca);
            }
            setMensagem("Tipo de Padrao de Onda não encontrado");
        } catch (SQLException ex) {
            setMensagem("Problemas ao localizar Tipo Padrao. Erro de SQL " + ex.getMessage());
        } finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        
        return (null);
    }

    public ArrayList<TipoPadrao> lista(){
        String sql = "select * from tipopadrao ";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        ArrayList <TipoPadrao> ls = new ArrayList<TipoPadrao>();

        try {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                TipoPadrao tpo = new TipoPadrao();
                tpo.setCodTipoPadrao(rs.getInt("codTipoPadrao"));
                tpo.setDesc(rs.getString("desc"));
                ls.add(tpo);
            }
            if (ls.isEmpty()){
                setMensagem("Relação de Tipo de Padrao de Onda vazia");
                return (ls);
            }
            setMensagem("Relação de Tipo de Padrao de Onda gerada com sucesso");
            return (ls);
        } catch (SQLException ex) {
            setMensagem("Problemas ao gerar relação de Tipo de Padrao de Onda. Erro de SQL " + ex.getMessage());
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
        String sql = "select LAST_INSERT_ID() as ultima from tipopadrao; ";

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
