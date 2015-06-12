

package persistencia;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Responsavel;


/**
 *
 * @author Eduardo
 * Modified by: rebonatto
 */
public class ResponsavelDAO{

    private String mensagem;
//c auto increment
    public boolean Insere (Responsavel mca){
        String sql = "insert into responsavel (`desc`)  values (?)";
//        INSERT INTO `tcc`.`Responsavel`     (`codResponsavel`, `desc`) VALUES (4, 'Oi');
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {            
            //ps.setInt(1, mca.getCodResp());
            ps.setString(1, mca.getDesc());

            int x = ps.executeUpdate();
            if (x > 0){
                setMensagem("Inseriu Responsavel com sucesso");
                return true;
            }
        } catch (SQLException e) {
            setMensagem("Problemas ao inserir Responsavel" + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
    }

     public boolean Altera (Responsavel mca){

         String sql = "update responsavel set "+
                      "`desc` = ?"
                      +"where `codResp` = ?";
         PreparedStatement ps = Conexao.getPreparedStatement(sql);
         try {           
           ps.setString(1, mca.getDesc());
           ps.setInt(2, mca.getCodResp());
            
            ps.execute();
            setMensagem("alterou Responsavel com sucesso");
            return true;
        } catch (SQLException e) {
            setMensagem("Problemas ao alterar Responsavel" + e.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return false;
     }

     public boolean exclui (Responsavel mca) {
        // Preparação do SQL para interagir com o banco
        String sql = "delete from responsavel where codResp = ?";
        
        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        try {
            ps.setInt(1, mca.getCodResp());

            if (ps.executeUpdate() > 0){
                setMensagem("Excluiu Responsavel com sucesso") ;
                return true;
            }

            setMensagem("Não encontrou Responsavel");
        }catch (SQLException e){
           setMensagem("Problemas ao excluir Responsavel" + e.getMessage());
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
    public Responsavel localiza(int codResponsavel){
        String sql = "select * from responsavel " +
                     "where codResp = ?";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);

        try {
            ps.setInt(1, codResponsavel);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Responsavel mca = new Responsavel();
                mca.setCodResp(rs.getInt("codResp"));
                mca.setDesc(rs.getString("desc"));
                setMensagem("Responsavel localizado com sucesso");
                return (mca);
            }
            setMensagem("Responsavel não encontrado");
        } catch (SQLException ex) {
            setMensagem("Problemas ao localizar Responsavel. Erro de SQL " + ex.getMessage());
        }finally{
            try {
                ps.close();
            } catch (SQLException ex) {
                setMensagem("Problemas ao Fechar PrepareStatement " + ex.getMessage());
            }
        }
        return (null);
    }

    public ArrayList<Responsavel> lista(){
        String sql = "select * from responsavel ";

        PreparedStatement ps = Conexao.getPreparedStatement(sql);
        ArrayList <Responsavel> ls = new ArrayList<Responsavel>();


        try {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Responsavel mca = new Responsavel();
                mca.setCodResp(rs.getInt("codResp"));
                mca.setDesc(rs.getString("desc"));
                ls.add(mca);
            }
            if (ls.isEmpty()){
                setMensagem("Relação de Responsavel vazia");
                return (ls);
            }
            setMensagem("Relação de Responsavel gerada com sucesso");
            return (ls);
        } catch (SQLException ex) {
            setMensagem("Problemas ao gerar relação de Responsavel. Erro de SQL " + ex.getMessage());
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
        String sql = "select LAST_INSERT_ID() as ultima from responsavel ";

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
            setMensagem("Problemas ao localizar Chave em Responsavel. Erro de SQL " + ex.getMessage());
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

